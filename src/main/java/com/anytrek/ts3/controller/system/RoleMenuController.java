package com.anytrek.ts3.controller.system;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
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

import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.SysMenuMapper;
import com.anytrek.ts3.model.SysMenu;

/**
 * 菜单控制器
 * 
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/menuManager")
public class RoleMenuController extends ControllerBase {

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(RoleController.class);

	@Autowired
	private SysMenuMapper menuMapper;

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = { "/getAllMenuList" }, method = RequestMethod.GET)
	public HashMap<String, Object> getAllMenuList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		Example example = new Example(SysMenu.class);
//		//注意：排序使用的是列名
//		example.setOrderByClause("parent_id asc, order_num asc");
//		List<SysMenu> list = menuMapper.selectByExample(example);
		List<SysMenu> list = menuMapper.getAllMenuList();
		HashMap<String, Object> result = new HashMap<>();
		result.put("list", list);
		return result;
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = { "/getMenuListByRoleId" }, method = RequestMethod.GET)
	public HashMap<String, Object> getMenuListByRoleId(@RequestParam(value = "roleId", required = true) Integer roleId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysMenu> list = menuMapper.getMenuListByRoleId(roleId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("list", list);
		return result;
	}

	@RequiresPermissions("sys:menu:add")
	@RequestMapping(value = { "/addMenu" }, method = RequestMethod.POST)
	public @ResponseBody SysMenu addMenu(@RequestBody SysMenu menu) {
		if (StringUtils.isEmpty(menu.getMenuName())) {
			throw new WebException(ErrorCode.MENU_PARAMETER_ERROR);
		}
		menu.setFlag(FlagConstants.FLAG_UNDELETED);
		menu.setCreateTime(new Timestamp(new Date().getTime()));
		int row = menuMapper.insertSelective(menu);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}
		return menu;
	}

	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = { "/editMenu" }, method = RequestMethod.POST)
	public @ResponseBody SysMenu editMenu(@RequestBody SysMenu menu) {
		if (StringUtils.isEmpty(menu.getMenuName())) {
			throw new WebException(ErrorCode.BAD_REQUEST);
		}
		int row = menuMapper.updateByPrimaryKeySelective(menu);
		if (row == 0) {
			throw new WebException(ErrorCode.SERVER_ERROR, "Error Data!");
		}
		return menu;
	}

	@RequiresPermissions("sys:menu:delete")
	@RequestMapping(value = { "/deleteMenu" }, method = RequestMethod.GET)
	public void delete(@RequestParam(value = "menuId", required = true) Integer menuId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (menuId == null || menuId == 0) {
			throw new WebException(ErrorCode.MENU_PARAMETER_ERROR);
		}
		int row = menuMapper.deleteMenu(menuId);
		if (row == 0) {
			throw new WebException(ErrorCode.MENU_NOT_FOUND);
		}
		sendOk();
	}

}
