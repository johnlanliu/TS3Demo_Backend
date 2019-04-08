package com.anytrek.ts3.controller.organization;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.dto.UserDetailDto;
import com.anytrek.ts3.dto.View;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.SysRoleMapper;
import com.anytrek.ts3.mapper.UserMapper;
import com.anytrek.ts3.model.SysRole;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 组织管理模块--用户接口
 * 
 * @author John date 2018 M10 9
 */

@RestController
@RequestMapping("/orgMUser")
public class UserController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SysRoleMapper roleMapper;

	/**
	 * 查询表t_user数据
	 * 
	 * @username 用户名
	 * @orgId 组织id（查询该id及其下属id的信息）
	 * 
	 */
	@RequiresPermissions("org:user:view")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/getUserList" }, method = RequestMethod.GET)
	public HashMap<String, Object> getUserList(@RequestParam(value = "orgId") Long orgId,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "pageindex") int pageindex, @RequestParam(value = "pagesize") int pagesize)
			throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		if (keyword != null & !"".equals(keyword)) {
			params.put("keyword", keyword);
		}
		if (roleId != null) {
			params.put("roleId", roleId);
		}
		params.put("orgId", orgId);

		// 默认页码为20，页数为第一页
		int psize = 20;
		int pindex = 1;
		if (pagesize > 0)
			psize = pagesize;
		if (pageindex > 0)
			pindex = pageindex;
		// 获取当前分页设备列表
		PageHelper.startPage(pindex, psize);
		List<UserDetailDto> viewList = userMapper.getUserListByParams(params);
		// 获取分页类数据
		PageInfo<UserDetailDto> appsPageInfo = new PageInfo<>(viewList);
		// 前端应急处理
		HashMap<String, Object> list = new HashMap<>();
		list.put("list", viewList);
		list.put("totalRows", appsPageInfo.getTotal());
		return list;
	}

	@RequiresPermissions("org:user:view")
	@RequestMapping(value = { "/getUserById" }, method = RequestMethod.GET)
	@JsonView(View.SummaryWithDetail.class)
	public User getUserById(@RequestParam(value = "userId", required = true) Long userId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new WebException(ErrorCode.USER_NOT_FOUND);
		}
		return user;
	}
	
	@RequiresPermissions("org:user:view")
	@RequestMapping(value = { "/getValidRoleList" }, method = RequestMethod.GET)
	@JsonView(View.SummaryWithDetail.class)
	public  List<SysRole> getValidRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		// 查询用户的权限
		SimplePrincipalCollection coll = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
		User loginUser = JSON.parseObject(userStr, User.class);
		SysRole role = roleMapper.selectByPrimaryKey(loginUser.getRoleId());
		Object[] roleIds = (Object[])(JSONArray.parseArray(role.getValidUserTypes()).toArray());
		List<SysRole> list = userMapper.getValidRoleList(roleIds);
		return list;
	}


	/**
	 * 新增表t_user数据
	 * 
	 * 
	 */
	// 访问路径 /orgMUser/addUser
	@RequiresPermissions("org:user:add")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody(required = false) User requestUser) throws Exception {
//		String email = requestUser.getEmail();
		Integer orgId = requestUser.getOrgId();
		Integer roleId = requestUser.getRoleId();

		if (orgId == null || orgId <= 0 || roleId == null || roleId <= 0) {
			throw new WebException(ErrorCode.USER_PARAM_ERROR);
		}
		// 判断username和email是否存在
		if (userMapper.getRepeatUser(requestUser) != null) {
			throw new WebException(ErrorCode.USER_EMAIL_REPEAT);
		}
		requestUser.setPassword(PasswordUtil.generate(requestUser.getPassword()));
		requestUser.setFlag(FlagConstants.FLAG_UNDELETED);
		requestUser.setCreateTime(new Timestamp(new Date().getTime()));
		int row = userMapper.insertSelective(requestUser);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}
		return requestUser;
	}

	/**
	 * 修改表t_user数据
	 * 
	 * 
	 */
	// 访问路径 /orgMUser/editUser
	@RequiresPermissions("org:user:edit")
	@JsonView(View.SummaryWithDetail.class)
	@RequestMapping(value = { "/editUser" }, method = RequestMethod.POST)
	public @ResponseBody User editUser(@RequestBody(required = false) User requestUser) throws Exception {

//		String email = requestUser.getEmail();
		Integer orgId = requestUser.getOrgId();
		Integer roleId = requestUser.getRoleId();
		if (orgId == null || orgId <= 0) {
			throw new WebException(ErrorCode.USER_PARAM_ERROR);
		}

		// 先查找再进行差分更新
		User curUser = userMapper.selectByPrimaryKey(requestUser.getUserId());
		if (curUser == null) {
			throw new WebException(ErrorCode.USER_NOT_FOUND);
		}
		// 判断username和email是否存在
		User repeatUser = userMapper.getRepeatUser(requestUser);
		if (repeatUser != null) {
			throw new WebException(ErrorCode.USER_REPEAT);
		}
		curUser.setUsername(requestUser.getUsername());
		curUser.setPhone(requestUser.getPhone());
		curUser.setEmail(requestUser.getEmail());
		curUser.setOrgId(orgId);
		curUser.setRoleId(roleId);
		curUser.setActivated(requestUser.getActivated());
		if (!StringUtils.isEmpty(requestUser.getPassword())) {
			curUser.setPassword(PasswordUtil.generate(requestUser.getPassword()));
		}

		userMapper.updateByPrimaryKeySelective(curUser);
		return curUser;
	}

	@RequiresPermissions("org:user:delete")
	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.GET)
	public void deleteUser(@RequestParam(value = "userId", required = true) Integer userId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (userId == null || userId == 0) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		User loginUser = this.getUserByHeader();
		if (userId.intValue() == loginUser.getUserId().intValue()) {
			throw new WebException(ErrorCode.USER_CAN_NOT_DELETE_SELF);
		}
		int row = userMapper.deleteUser(userId);
		if (row == 0) {
			throw new WebException(ErrorCode.USER_NOT_FOUND);
		}
		sendOk();
	}

	/**
	 * 给指定邮箱发送激活邮件
	 * 
	 * @email 邮箱
	 * @userId 用户id
	 * @throws Exception
	 * 
	 */
	@RequiresPermissions("org:user:activate")
	@RequestMapping(value = { "/sendActiveEmail" }, method = RequestMethod.GET)
	public void sendActiveEmail(@RequestParam(value = "userId", required = true) String userId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.sendOk();
	}
}
