package com.anytrek.ts3.controller.system;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.SysRoleMapper;
import com.anytrek.ts3.mapper.SysRoleMenuMapper;
import com.anytrek.ts3.mapper.UserMapper;
import com.anytrek.ts3.model.SysRole;
import com.anytrek.ts3.model.SysRoleMenu;

/**
 * 角色管理模块
 * 
 * 
 * @author aleen date 2018 Oct 2
 */

@RestController
@RequestMapping("/roleManager")
public class RoleController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(RoleController.class);

	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 获取角色列表 不分页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = { "/getAllRoleList" }, method = RequestMethod.GET)
	public List<SysRole> getAllRoleList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<SysRole> viewList = roleMapper.getAllRoleList();
		return viewList;
	}


	// 添加角色信息
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value = { "/addRole" }, method = RequestMethod.POST)
	public String addRole(@RequestBody String reqStr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject req = JSONObject.parseObject(reqStr);
		// 添加角色信息必须包含菜单权限
		if (!req.containsKey("menuIds") || !req.containsKey("role")) {
			throw new WebException(ErrorCode.ROLE_PARAMETER_ERROR);
		}
		JSONObject roleObj = req.getJSONObject("role");
		JSONArray menuJsonArray = req.getJSONArray("menuIds");
		if (StringUtils.isEmpty(roleObj.getString("roleName"))) {
			throw new WebException(ErrorCode.ROLE_PARAMETER_ERROR);
		}

		// 创建角色实例
		SysRole role = new SysRole();
		role.setRoleName(roleObj.getString("roleName"));
		if (roleObj.containsKey("remark"))
			role.setRemark(roleObj.getString("remark"));
		role.setFlag(FlagConstants.FLAG_UNDELETED);
		role.setCreateTime(new Timestamp(new Date().getTime()));
		// 插入新的角色数据
		int row = roleMapper.insertSelective(role);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}

		List<SysRoleMenu> roleMenuList = new ArrayList<>();

		// 保存menuIds数组
		JSONArray menuArray = new JSONArray();
		if (menuJsonArray.size() != 0) {
			for (int i = 0; i < menuJsonArray.size(); i++) {
				Integer menuId = (Integer) menuJsonArray.get(i);
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setRoleId(role.getRoleId());
				roleMenu.setMenuId(menuId);
				roleMenu.setCreateTime(new Timestamp(new Date().getTime()));
				menuArray.add(menuId);
				roleMenuList.add(roleMenu);
			}
		}
		// 批量插入角色菜单关联表数据
		roleMenuMapper.insertList(roleMenuList);
		JSONObject result = new JSONObject();
		result.put("role", (JSONObject)JSONObject.toJSON(role));
		result.put("menuIds", menuArray);
		return result.toString();
	}

	// 修改角色信息
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = { "/editRole" }, method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String editRole(@RequestBody String reqStr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject req = JSONObject.parseObject(reqStr);
		// 添加角色信息必须包含菜单权限
		if (!req.containsKey("menuIds") || !req.containsKey("role")) {
			throw new WebException(ErrorCode.ROLE_PARAMETER_ERROR);
		}

		JSONObject roleObj = req.getJSONObject("role");
		JSONArray menuJsonArray = req.getJSONArray("menuIds");
		if (StringUtils.isEmpty(roleObj.getString("roleName"))) {
			throw new WebException(ErrorCode.ROLE_PARAMETER_ERROR);
		}
		Integer roleId = roleObj.getInteger("roleId");
		SysRole role = roleMapper.selectByPrimaryKey(roleId);
		role.setRoleName(roleObj.getString("roleName"));
		role.setValidOrgTypes(roleObj.getJSONArray("validOrgTypes").toString());
		role.setValidUserTypes(roleObj.getJSONArray("validUserTypes").toString());
		role.setRemark(roleObj.getString("remark"));
		// 更新数据库角色对象
		int row = roleMapper.updateByPrimaryKey(role);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}
		// 从数据库获取更新后角色对象
		SysRole sysRole = roleMapper.selectByPrimaryKey(role.getRoleId());
		// 清空角色所有菜单权限
		roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
		List<SysRoleMenu> roleMenuList = new ArrayList<>();
		JSONArray menuArray = new JSONArray();
		if (menuJsonArray.size() != 0) {
			for (int i = 0; i < menuJsonArray.size(); i++) {
				Integer menuId = (Integer) menuJsonArray.get(i);
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setRoleId(role.getRoleId());
				roleMenu.setMenuId(menuId);
				roleMenu.setCreateTime(new Timestamp(new Date().getTime()));
				menuArray.add(menuId);
				roleMenuList.add(roleMenu);
			}
		}
		// 插入角色新的菜单权限
		roleMenuMapper.insertList(roleMenuList);
		JSONObject result = new JSONObject();
		result.put("role", (JSONObject)JSONObject.toJSON(sysRole));
		result.put("menuIds", menuArray);
		return result.toString();
	}

	// 删除角色信息
	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value = { "/deleteRole" }, method = RequestMethod.GET)
	public void deleteRole(@RequestParam(value = "roleId", required = true) Integer roleId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (roleId == null || roleId == 0) {
			throw new WebException(ErrorCode.ROLE_PARAMETER_ERROR);
		}
		Integer count = userMapper.getUserCountByRoleId(roleId);
		if (count > 0) {
			throw new WebException(ErrorCode.ROLE_HAS_USER);
		}
		int row = roleMapper.deleteRole(roleId);
		if (row == 0) {
			throw new WebException(ErrorCode.ROLE_NOT_FOUND);
		}
		// 清空角色所有菜单权限
		// roleMenuMapper.deleteRoleMenuByRoleId(roleId);
		sendOk();
	}
}
