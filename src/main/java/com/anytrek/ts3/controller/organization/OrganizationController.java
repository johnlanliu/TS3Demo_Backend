package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.common.OrgActivateType;
import com.anytrek.ts3.common.OrgAttribute;
import com.anytrek.ts3.dto.OrganizationDetailDto;
import com.anytrek.ts3.dto.View;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.OrganizationMapper;
import com.anytrek.ts3.mapper.TrkInfoMapper;
import com.anytrek.ts3.mapper.UserMapper;
import com.anytrek.ts3.model.Organization;
import com.anytrek.ts3.model.User;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 组织模块--组织管理接口
 * 
 * @author aleen date 2018 Oct 2
 */

@RestController("OrgMOrgController")
@RequestMapping("/orgMOrganization")
public class OrganizationController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationMapper orgMapper;
	@Autowired
	private TrkInfoMapper trkInfoMapper;
	@Autowired
	private UserMapper userMapper;

	// 根据orgId获取t_organization 该节点及其下属节点
	@RequiresPermissions("org:view")
	@JsonView(View.Summary.class)
	@RequestMapping(value = { "/getOrgList" }, method = RequestMethod.GET)
	public @ResponseBody List<OrganizationDetailDto> getOrganizationList(
			@RequestParam(value = "orgId", required = true) Integer orgId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (orgId == null || orgId == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// HashMap<String, Object> params = new HashMap<>();
		// params.put("orgId", orgId);
		User loginUser = getUserByHeader();
		List<OrganizationDetailDto> orgList = orgMapper.getChildrenList(orgId);
		if (orgList == null || orgList.size() == 0) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}
		// 判断改用户是否有权限获取组织信息
		this.checkUserOrgPermission(orgId, loginUser);

		for (OrganizationDetailDto organization : orgList) {
			String parents = organization.getParents();
			Integer parentId = this.getParentId(parents);
			organization.setParentId(parentId);
			// 此接口会频繁使用， 下面的代码极大的增加了数据库压力， 没必要！！
			// if (parentId != 0) {
			// Organization org = orgMapper.selectByPrimaryKey(parentId);
			// organization.setParentName(org.getOrgName());
			// } else {
			// organization.setParentName("");
			// }
		}
		return orgList;
	}

	// 添加Partner 区别于添加普通节点 需激活
	@RequiresPermissions("org:add-partner")
	@JsonView(View.SummaryWithDetail.class)
	@PostMapping(value = { "/addPartner" })
	public @ResponseBody Organization addPartner(@RequestBody Organization requestOrg, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (StringUtils.isEmpty(requestOrg.getOrgName())) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// 判断email是否存在
		if (!StringUtils.isEmpty(requestOrg.getEmail()) && orgMapper.getOrgByEmail(requestOrg.getEmail()) != null) {
			throw new WebException(ErrorCode.COMPANY_EMAIL_REPEAT);
		}
		if (!StringUtils.isEmpty(requestOrg.getEmail()) && userMapper.getUserByEmail(requestOrg.getEmail()) != null) {
			throw new WebException(ErrorCode.COMPANY_EMAIL_REPEAT);
		}
		Organization parentOrg = orgMapper.selectByPrimaryKey(requestOrg.getParentId());
		if (parentOrg == null) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND, "Can't find parent!");
		}
		String parents = this.getParents(requestOrg.getParentId());
		requestOrg.setParents(parents);
		requestOrg.setFlag(FlagConstants.FLAG_UNDELETED);
		requestOrg.setCreateTime(new Timestamp(new Date().getTime()));
		requestOrg.setActivated(OrgActivateType.UNACTIVATED);
		requestOrg.setAttribute(OrgAttribute.PARTNER);
		requestOrg.setPushurl(parentOrg.getPushurl());
		requestOrg.setNeedPushOurServer(parentOrg.getNeedPushOurServer());
		requestOrg.setNeedRetry(parentOrg.getNeedRetry());
		String accessKey = UUID.randomUUID().toString().replace("-", "");
		requestOrg.setAccessKey(accessKey);
		int row = orgMapper.insertSelective(requestOrg);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR);
		}
		return requestOrg;
	}

	// 添加普通节点 区别于添加partner 无需激活
	@RequiresPermissions("org:add-sub-node")
	@JsonView(View.SummaryWithDetail.class)
	@PostMapping(value = { "/addOrg" })
	public @ResponseBody Organization addOrg(@RequestBody Organization requestOrg, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (StringUtils.isEmpty(requestOrg.getOrgName())) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// 判断email是否存在
		if (!StringUtils.isEmpty(requestOrg.getEmail()) && orgMapper.getOrgByEmail(requestOrg.getEmail()) != null) {
			throw new WebException(ErrorCode.COMPANY_EMAIL_REPEAT);
		}
		if (!StringUtils.isEmpty(requestOrg.getEmail()) && userMapper.getUserByEmail(requestOrg.getEmail()) != null) {
			throw new WebException(ErrorCode.COMPANY_EMAIL_REPEAT);
		}
		Organization parentOrg = orgMapper.selectByPrimaryKey(requestOrg.getParentId());
		if (parentOrg == null) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND, "Can't find parent!");
		}
		String parents = this.getParents(requestOrg.getParentId());
		requestOrg.setParents(parents);
		requestOrg.setFlag(FlagConstants.FLAG_UNDELETED);
		requestOrg.setCreateTime(new Timestamp(new Date().getTime()));
		requestOrg.setPushurl(parentOrg.getPushurl());
		requestOrg.setNeedPushOurServer(parentOrg.getNeedPushOurServer());
		requestOrg.setNeedRetry(parentOrg.getNeedRetry());
		String accessKey = UUID.randomUUID().toString().replace("-", "");
		requestOrg.setAccessKey(accessKey);
		int row = orgMapper.insertSelective(requestOrg);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR);
		}
		return requestOrg;
	}

	// 修改组织信息
	@RequiresPermissions("org:edit")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/editOrg" }, method = RequestMethod.POST)
	public @ResponseBody Organization editOrganization(@RequestBody Organization requestOrg, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User loginUser = getUserByHeader();
		if (requestOrg.getOrgId() == null || requestOrg.getOrgId() == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// 判断改用户是否有权限获取组织信息
		this.checkUserOrgPermission(requestOrg.getOrgId(), loginUser);
		// 判断email是否存在
		// if (!StringUtils.isEmpty(requestOrg.getEmail()) &&
		// orgMapper.getOrgByEmail(requestOrg.getEmail()) != null) {
		// throw new WebException(ErrorCode.COMPANY_EMAIL_REPEAT);
		// }
		// if (!StringUtils.isEmpty(requestOrg.getEmail()) &&
		// userMapper.getUserByEmail(requestOrg.getEmail()) != null) {
		// throw new WebException(ErrorCode.COMPANY_EMAIL_REPEAT);
		// }
		Organization organization = orgMapper.selectByPrimaryKey(requestOrg.getOrgId());
		Organization parent = orgMapper.selectByPrimaryKey(requestOrg.getParentId());
		if (parent != null && parent.getParents().indexOf("-" + requestOrg.getOrgId().toString() + "-") > -1) {
			throw new WebException(ErrorCode.COMPANY_NOT_CHANGE_PARENT_TO_SUBCOMPANY);
		}
		// org_id=1 为根节点不能调整树结构， 只能修改名称
		if (organization.getOrgId() != 1) {
			String parents = this.getParents(requestOrg.getParentId());

			if (!organization.getParents().equals(parents)) {

				HashMap<String, Object> param = new HashMap<>();
				param.put("parents", parents);
				param.put("oldParents", organization.getParents());
				param.put("orgId", organization.getOrgId());
				/** 修改公司的母公司时， 需修改该公司所有子公司的母公司 */
				orgMapper.changeChildrenOrgParents(param);
				organization.setParents(parents);
			}
		}
		organization.setOrgName(requestOrg.getOrgName());
		organization.setAttribute(requestOrg.getAttribute());
		organization.setContacts(requestOrg.getContacts());
		organization.setEmail(requestOrg.getEmail());
		organization.setPhone(requestOrg.getPhone());
		organization.setStreetAddress(requestOrg.getStreetAddress());
		organization.setActivated(requestOrg.getActivated());
		organization.setModifyTime(new Timestamp(System.currentTimeMillis()));
		int row = orgMapper.updateByPrimaryKey(organization);
		if (row == 0) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}

		requestOrg.setParentId(this.getParentId(organization.getParents()));
		return organization;
	}

	// 修改pushurl
	@RequiresPermissions("org:edit-setting")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/editPushUrl" }, method = RequestMethod.POST)
	public void editPushUrl(@RequestBody Organization requestOrg, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (requestOrg.getOrgId() == null || requestOrg.getOrgId() == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// 判断改用户是否有权限获取组织信息
		User loginUser = getUserByHeader();
		this.checkUserOrgPermission(requestOrg.getOrgId(), loginUser);

		Organization organization = orgMapper.selectByPrimaryKey(requestOrg.getOrgId());
		organization.setPushurl(requestOrg.getPushurl());
		organization.setNeedPushOurServer(requestOrg.getNeedPushOurServer());
		organization.setNeedRetry(requestOrg.getNeedRetry());
		organization.setModifyTime(new Timestamp(System.currentTimeMillis()));
		orgMapper.updateByPrimaryKey(organization);
		sendOk();
	}

	// 更新accesskey
	@RequiresPermissions("org:edit-setting")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/renewApiKey" }, method = RequestMethod.POST)
	public void renewApiKey(@RequestParam(value = "orgId", required = true) Integer orgId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (orgId == null || orgId == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// 判断改用户是否有权限获取组织信息
		User loginUser = getUserByHeader();
		this.checkUserOrgPermission(orgId, loginUser);

		JSONObject result = this.getOkJSONObject();
		Organization organization = orgMapper.selectByPrimaryKey(orgId);
		String accessKey = UUID.randomUUID().toString().replace("-", "");
		organization.setAccessKey(accessKey);
		orgMapper.updateByPrimaryKey(organization);
		result.put("accessKey", accessKey);
		sendJSON(result);
	}

	// 根据orgId获取t_organization 组织架构表数据
	@RequiresPermissions("org:view")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/getOrgById" }, method = RequestMethod.GET)
	public Organization getOrganizationById(@RequestParam(value = "orgId", required = true) Integer orgId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (orgId == null || orgId == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		// 判断改用户是否有权限获取组织信息
		User loginUser = getUserByHeader();
		this.checkUserOrgPermission(orgId, loginUser);

		Organization organization = orgMapper.selectByPrimaryKey(orgId);
		if (organization == null) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}
		Integer parentId = this.getParentId(organization.getParents());
		Organization parentOrg = orgMapper.selectByPrimaryKey(parentId);
		if (parentId != null && parentId != 0) {
			organization.setParentId(parentId);
			organization.setParentName(parentOrg.getOrgName());
		}
		return organization;
	}

	/**
	 * 发送Partner激活邮件
	 * 
	 * 
	 */
	@RequiresPermissions("org:activate")
	@RequestMapping(value = { "/sendActiveEmail" }, method = RequestMethod.GET)
	public void sendActiveEmail(@RequestParam(value = "orgId", required = true) Integer orgId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.sendOk();
	}

	// 删除组织
	@RequiresPermissions("org:delete")
	@RequestMapping(value = { "/deleteOrg" }, method = RequestMethod.GET)
	public void deleteOrganization(@RequestParam(value = "orgId", required = true) Integer orgId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (orgId == null || orgId == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		
		// 判断改用户是否有权限获取组织信息
		User loginUser = getUserByHeader();
		this.checkUserOrgPermission(orgId, loginUser);
		
		Organization organization = orgMapper.selectByPrimaryKey(orgId);
		if (organization == null) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}
		List<OrganizationDetailDto> childrenOrg = orgMapper.getChildrenList(orgId);
		// 因为要扣去他自身， 所以这个值一直 >=1
		if (childrenOrg.size() > 1) {
			throw new WebException(ErrorCode.COMPANY_NOT_DELETE_HAVE_CHILD);
		}
		HashMap<String, Object> params = new HashMap<>();
		params.put("orgId", orgId);
		if (trkInfoMapper.getDeviceListByParams(params).size() > 0) {
			throw new WebException(ErrorCode.COMPANY_NOT_DELETE_HAVE_DEVICE);
		}
		if (userMapper.getUserListByParams(params).size() > 0) {
			throw new WebException(ErrorCode.COMPANY_NOT_DELETE_HAVE_USER);
		}
		int row = orgMapper.deleteOrg(orgId);
		if (row == 0) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}
		sendOk();
	}

	private Integer getParentId(String parents) {
		Integer parentId = 0;
		if (parents != null && !"".equals(parents) && !"-".equals(parents)) {
			String[] _parentStrArr = parents.split("-");
			parentId = Integer.parseInt(_parentStrArr[_parentStrArr.length - 1]);
		}
		return parentId;
	}

	/**
	 * 获取组织的父节点， 并生成组织祖先ID树： parents字段
	 * 
	 * @param parentId
	 * @return
	 */
	private String getParents(Integer parentId) {
		if (parentId == null || parentId == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR, "Company's parentId is null!");
		}
		Organization parentOrg = orgMapper.selectByPrimaryKey(parentId);
		if (parentOrg == null) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR, "Company's parent is null!");
		}
		String parents = this.generateParents(parentOrg.getParents(), parentOrg.getOrgId());
		return parents;
	}

	/**
	 * 生成Parents 格式： -1-15-34-
	 * 
	 * @param parents
	 * @param orgId
	 * @return
	 */
	private String generateParents(String parents, Integer orgId) {
		return parents + orgId + "-";
	}
}
