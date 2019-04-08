package com.anytrek.ts3.controller.auth;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anytrek.ts3.ControllerBase;
import com.anytrek.ts3.common.FlagConstants;
import com.anytrek.ts3.common.OrgActivateType;
import com.anytrek.ts3.common.UserActivateType;
import com.anytrek.ts3.common.UserRoleType;
import com.anytrek.ts3.exception.ErrorCode;
import com.anytrek.ts3.exception.WebException;
import com.anytrek.ts3.mapper.SysMenuMapper;
import com.anytrek.ts3.mapper.SysRoleMapper;
import com.anytrek.ts3.mapper.OrganizationMapper;
import com.anytrek.ts3.mapper.UserMapper;
import com.anytrek.ts3.model.SysMenu;
import com.anytrek.ts3.model.SysRole;
import com.anytrek.ts3.model.Organization;
import com.anytrek.ts3.model.User;
import com.anytrek.util.PasswordUtil;
import com.anytrek.util.ValidateUtil;

/**
 * 鉴权模块接口
 * 
 * @author John date 2018 M09 16
 */
@RestController
@RequestMapping("/author")
public class AuthorizeController extends ControllerBase {

	private static Logger logger = LogManager.getLogger(AuthorizeController.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private OrganizationMapper orgMapper;
	@Autowired
	private SysMenuMapper menuMapper;

	/**
	 * 访问路径 /author/login 网站登录接口
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String toLogin(@RequestParam(value = "user", required = true) String username,
			@RequestParam(value = "psw", required = true) String password,
			@RequestParam(value = "refreshperiod", required = false) String refreshperiod, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			// 跳转到MyShiroRealm的doGetAuthenticationInfo方法进行登录验证
			subject.login(token);
		} catch (LockedAccountException lae) {
			token.clear();
			// 账号未激活
			throw new WebException(ErrorCode.USER_UNACTIVATED);
		} catch (UnknownAccountException ex) {
			token.clear();
			// 账号未激活
			throw new WebException(ErrorCode.USER_NOT_FOUND);
		} catch (IncorrectCredentialsException e) {
			token.clear();
			// 账号或密码不存在
			throw new WebException(ErrorCode.USER_PASSWORD_INCORRECT);
		} catch (AuthenticationException e) {
			logger.error("", e);
			token.clear();
			// 账号或密码不存在
			throw new WebException(ErrorCode.USER_PASSWORD_INCORRECT);
		}

		Session session = SecurityUtils.getSubject().getSession();
		// 查询用户的权限
		SimplePrincipalCollection coll = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
		User loginUser = JSON.parseObject(userStr, User.class);
		SysRole role = roleMapper.selectByPrimaryKey(loginUser.getRoleId());

		// 根据用户名获取所有菜单对象
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		if (loginUser.getRoleId() != 1)
			menuList = menuMapper.getMenuByUserId(loginUser.getUserId());
		else
			menuList = menuMapper.getAllMenuList();
		JSONObject responseJSON = new JSONObject();
		responseJSON.put("token", subject.getSession().getId());
		responseJSON.put("orgId", loginUser.getOrgId());
		responseJSON.put("roleId", loginUser.getRoleId());
		responseJSON.put("userId", loginUser.getUserId());
		responseJSON.put("username", username);
		responseJSON.put("roleName", role.getRoleName());
		responseJSON.put("validOrgTypes", role.getValidOrgTypes());
		responseJSON.put("validUserTypes", role.getValidUserTypes());
		JSONArray menuArr = new JSONArray();
		for (SysMenu menu : menuList) {
			JSONObject menuObj = new JSONObject();
			menuObj.put("menuId", menu.getMenuId());
			menuObj.put("parentId", menu.getParentId());
			menuObj.put("menuName", menu.getTitle());
			menuObj.put("component", menu.getComponent());
			menuObj.put("url", menu.getUrl());
			menuObj.put("perms", menu.getPerms());
			menuObj.put("type", menu.getType());
			menuObj.put("orderNum", menu.getOrderNum());
			menuObj.put("icon", menu.getIcon());
			menuArr.add(menuObj);
		}
		responseJSON.put("status", ErrorCode.OK);
		responseJSON.put("menuList", menuArr);
//		sendJSON(responseJSON);
		return responseJSON.toJSONString();
		
	}

	/**
	 * 访问路径 /author/login 网站登录接口
	 */
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public void toLogout() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		sendOk();
	}

	/**
	 * 检查登录状态，返回权限和菜单列表，
	 */
	@RequestMapping(value = { "/authToken" }, method = RequestMethod.GET)
	public HashMap<String, Object> authToken(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		User loginUser = this.getUserByHeader();
		// 根据用户名获取所有菜单对象
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		if (loginUser.getRoleId() != 1)
			menuList = menuMapper.getMenuByUserId(loginUser.getUserId());
		else
			menuList = menuMapper.getAllMenuList();
		// 根据菜单类型获取所有目录级别菜单
		List<SysMenu> sysMenuList = getTreeByUsernameAndMenuType(menuList, 1);
		// 获取该用户的所有权限
		Set<String> perms = new HashSet<>();
		for (SysMenu menu : menuList) {
			if (menu.getPerms() != null && !"".equals(menu.getPerms())) {
				perms.add(menu.getPerms());
			}
		}
		Integer orgId = loginUser.getOrgId();
		result.put("menuList", sysMenuList);
		result.put("permissions", perms);
		result.put("orgId", orgId);
		result.put("status", ErrorCode.OK);
		return result;
	}


	/**
	 * 激活组织账号
	 * 
	 * 
	 */
	// 访问路径 /author/organizationActive
	@RequestMapping(value = { "/organizationActive" }, method = RequestMethod.POST)
	public void organizationActive(@RequestBody String req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = JSONObject.parseObject(req);
		JSONObject orgObj = obj.getJSONObject("org");
		JSONObject userObj = obj.getJSONObject("user");
		String activationKey = orgObj.getString("activationKey");
		Organization org = orgMapper.getOrgByActivationKey(activationKey);
		if (org == null || org.getOrgId() == 0) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}
		org.setAccessKey("");
		org.setOrgName(orgObj.getString("orgName"));
		org.setContacts(orgObj.getString("contacts"));
		org.setPhone(orgObj.getString("phone"));
		org.setStreetAddress(orgObj.getString("streetAddress"));
		org.setPushurl(orgObj.getString("pushurl"));
		org.setActivated(OrgActivateType.ACTIVATED);
		orgMapper.updateByPrimaryKeySelective(org);

		User user = new User();
		user.setFlag(FlagConstants.FLAG_UNDELETED);
		user.setEmail(org.getEmail());
		user.setUsername(userObj.getString("username"));
		user.setOrgId(org.getOrgId());
		user.setPassword(PasswordUtil.generate(userObj.getString("password")));
		user.setActivated(UserActivateType.ACTIVATED);
		user.setRoleId(UserRoleType.PARTNER);
		if (userMapper.getRepeatUser(user) != null) {
			throw new WebException(ErrorCode.USER_REPEAT);
		}
		user.setKey("");
		userMapper.insertSelective(user);
		this.sendOk();
	}

	/**
	 * 激活组织页面，通过key获取组织
	 * 
	 * 
	 */
	@RequestMapping(value = { "/getOrgByKey" }, method = RequestMethod.GET)
	public Organization getOrgIdByKey(@RequestParam(value = "activationKey", required = true) String activationKey,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(activationKey)) {
			throw new WebException(ErrorCode.COMPANY_PARAMETER_ERROR);
		}
		Organization org = orgMapper.getOrgByActivationKey(activationKey);
		if (org == null) {
			throw new WebException(ErrorCode.COMPANY_NOT_FOUND);
		}
		return org;
	}


	/**
	 * 给指定邮箱发送邮件(找回密码)
	 * 
	 * @email 邮箱
	 * @userId 用户id
	 * 
	 */
	// 访问路径 /author/sendResetPwdEmail
	@RequestMapping(value = { "/sendResetPwdEmail" }, method = RequestMethod.GET)
	public void sendResetPwdEmail(@RequestParam(value = "email", required = true) String email) throws Exception {
		sendOk();
	}
	


	/**
	 * 
	 */
	@RequestMapping(value = { "/getUserByKey" }, method = RequestMethod.GET)
	public User getUserByKey(@RequestParam(value = "activationKey", required = true) String activationKey,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(activationKey)) {
			throw new WebException(ErrorCode.USER_PARAM_ERROR);
		}
		User user = userMapper.getUserByKey(activationKey);
		if (user == null) {
			throw new WebException(ErrorCode.USER_NOT_FOUND);
		}
		return user;
	}


	/**
	 * 找回密码--第二步， 根据第一步的key修改密码，同时清空key
	 * 
	 * 
	 */
	@RequestMapping(value = { "/resetPwd" }, method = RequestMethod.POST)
	public void resetPwd(@RequestBody String req) throws Exception {
		JSONObject jsonobject = JSONObject.parseObject(req);
		String key = jsonobject.getString("key");
		String newPwd = jsonobject.getString("password");
		if (StringUtils.isEmpty(newPwd)) {
			throw new WebException(ErrorCode.USER_PARAM_ERROR);
		}
		if (StringUtils.isEmpty(key)) {
			throw new WebException(ErrorCode.USER_PARAM_ERROR, "Key is null.");
		}
		User user = userMapper.getUserByKey(key);
		if (user == null) {
			throw new WebException(ErrorCode.USER_NOT_FOUND, "USER_NOT_FOUND");
		}
		user.setPassword(PasswordUtil.generate(newPwd));
		user.setActivated(UserActivateType.ACTIVATED);
		user.setModifyTime(new Timestamp(new Date().getTime()));
		user.setKey(null);
		userMapper.updateByPrimaryKey(user);
		this.sendOk();
	}

	/**
	 * 修改登陆用户自身的密码
	 * 
	 * 
	 */
	@RequestMapping(value = { "/editUserPwd" }, method = RequestMethod.POST)
	public void editUserPwd(@RequestBody String req) throws Exception {
		User loginUser = this.getUserByHeader();
		JSONObject jsonobject = JSONObject.parseObject(req);
		String oldPwd = jsonobject.getString("oldPwd");
		String newPwd = jsonobject.getString("newPwd");

		if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd)) {
			throw new WebException(ErrorCode.USER_PARAM_ERROR, "password is null.");
		}
		loginUser = userMapper.selectByPrimaryKey(loginUser.getUserId());
		if (loginUser == null) {
			throw new WebException(ErrorCode.USER_NOT_FOUND, "USER_NOT_FOUND");
		}
		// 比对密码
		Boolean b = PasswordUtil.verify(oldPwd, loginUser.getPassword());
		if (!b) {
			throw new WebException(ErrorCode.USER_PASSWORD_INCORRECT);
		}

		// 更新用户密码
		User user = new User();
		user.setUserId(loginUser.getUserId());
		user.setPassword(PasswordUtil.generate(newPwd));
		user.setModifyTime(new Timestamp(new Date().getTime()));
		userMapper.updateByPrimaryKeySelective(user);
		this.sendOk();
	}


	private List<SysMenu> getTreeByUsernameAndMenuType(List<SysMenu> menus, int menuType) throws Exception {
		List<SysMenu> sysMenus = new ArrayList<>();
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if (!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		getChildren(sysMenus, menus, menuType);
		return sysMenus;
	}

	private void getChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
		for (SysMenu SysMenu : SysMenus) {
			List<SysMenu> children = new ArrayList<>();
			for (SysMenu menu : menus) {
				if (menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue;
				}
				if (SysMenu.getMenuId() != null && SysMenu.getMenuId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getMenuName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if (!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			getChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
		boolean exist = false;
		for (SysMenu menu : sysMenus) {
			if (menu.getMenuId().equals(sysMenu.getMenuId())) {
				exist = true;
			}
		}
		return exist;
	}
}
