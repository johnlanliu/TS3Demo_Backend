package com.anytrek.ts3.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.anytrek.ts3.mapper.SysMenuMapper;
import com.anytrek.ts3.mapper.UserMapper;
import com.anytrek.ts3.model.SysMenu;
import com.anytrek.ts3.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
	@Resource
	private UserMapper userMapper;

	@Resource
	private SysMenuMapper menuMapper;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Session session = SecurityUtils.getSubject().getSession();
		// 查询用户的权限
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
		String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
		User user = JSON.parseObject(userStr, User.class);
		List<SysMenu> menuList = null;
		if (user.getRoleId() == 1)
			menuList = menuMapper.getAllMenuList();
		else
			menuList = menuMapper.getMenuByUserId(user.getUserId());
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> permissionList = new HashSet<>();
		for (SysMenu menu : menuList) {
			// authorizationInfo.addStringPermission(menu.getUrl());
			if (!StringUtils.isEmpty(menu.getPerms()))
				permissionList.add(menu.getPerms());
		}
		authorizationInfo.addStringPermissions((Collection<String>) permissionList);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		User user = userMapper.getUserByUsername(username);
		if (user == null)
			throw new UnknownAccountException();
		if (0 == user.getActivated()) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给ShiroConfig使用CredentialsMatcher进行密码匹配，自定义密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户
				user.getPassword(), // 密码
				// ByteSource.Util.bytes("username"), // salt=username+salt,采用明文访问时，不需要此句
				getName() // realm name
		);
		return authenticationInfo;
	}

	/**
	 * 自定义密码匹配规则
	 */
	@SuppressWarnings("unused")
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
			throws AuthenticationException {
		CredentialsMatcher cm = new com.anytrek.ts3.session.CredentialsMatcher();
		if (cm != null) {
			//
			if (!cm.doCredentialsMatch(token, info)) {
				// not successful - throw an exception to indicate this:
				String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
				throw new IncorrectCredentialsException(msg);
			}
		} else {
			throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify "
					+ "credentials during authentication.  If you do not wish for credentials to be examined, you "
					+ "can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
		}
	}

}
