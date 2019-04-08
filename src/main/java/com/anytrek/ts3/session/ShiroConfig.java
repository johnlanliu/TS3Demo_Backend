package com.anytrek.ts3.session;

import com.anytrek.ts3.mapper.SysMenuMapper;
import com.anytrek.ts3.model.SysMenu;
import com.github.pagehelper.util.StringUtil;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisSessionDAO;

/**
 * Created by yangqj on 2017/4/23.
 */
@Configuration
public class ShiroConfig {
	@Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * session过期时间
     */
    @Value("${spring.redis.expire}")
    private int expire;
    
    @Autowired
	private SysMenuMapper menuMapper;

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("authc", new MyShiroFilter());
		/*定义shiro过滤链  Map结构
		 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
		 * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
		 * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		 */
		shiroFilterFactoryBean.setFilters(filterMap);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        /* 过滤链定义，从上向下顺序执行，一般将 / ** 放在最为下边:这是一个坑呢，一不小心代码就不好使了;
        authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问 */
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/common/getguid", "anon");
		filterChainDefinitionMap.put("/author/login", "anon");
		filterChainDefinitionMap.put("/author/authToken", "anon");
		filterChainDefinitionMap.put("/author/sendResetPwdEmail", "anon");
		filterChainDefinitionMap.put("/author/getUserByKey", "anon");
		filterChainDefinitionMap.put("/author/resetPwd", "anon");
		filterChainDefinitionMap.put("/author/organizationActive", "anon");
		filterChainDefinitionMap.put("/author/getOrgByKey", "anon");
		filterChainDefinitionMap.put("/author/logout", "anon");
		filterChainDefinitionMap.put("/monitorMShare/showShare", "anon");
		filterChainDefinitionMap.put("/error", "anon");
		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //自定义加载权限资源关系
        List<SysMenu> menuList = menuMapper.getAllMenuList();
         for(SysMenu menu:menuList){
            if (StringUtil.isNotEmpty(menu.getPerms())) {
                String permission = "perms[" + menu.getPerms()+ "]";
                filterChainDefinitionMap.put(menu.getPerms(),permission);
            }
        }
		
		filterChainDefinitionMap.put("/**", "authc");
//		filterChainDefinitionMap.put("/**", "anon");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    /**
	 * 二.权限管理
	 * @Title: securityManager
	 * @Description: SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理
	 * @return SecurityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setSessionManager(sessionManager());
		//注入记住我管理器
	    securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	
	/**
	 * 1.自定义认证
	 * @Title: myShiroRealm
	 * @Description: ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理
	 * @return MyShiroRealm
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}
	
	/**
	 * 密码凭证匹配器，作为自定义认证的基础
	 *  （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * 2.自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
	 */
	@Bean
	public SessionManager sessionManager() {
		MySessionManager mySessionManager = new MySessionManager();
		mySessionManager.setSessionDAO(redisSessionDAO());
		return mySessionManager;
	}
	
	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * @ConfigurationProperties(prefix = "spring.redis")
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(expire);
		redisManager.setTimeout(timeout);
		return redisManager;
	}
	
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		// 自定义session管理 使用redis
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	
	/**
	 * 3.此处对应前端“记住我”的功能，获取用户关联信息而无需登录
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
	      //这个参数是cookie的名称，对应前端的checkbox的name = remember
	      SimpleCookie simpleCookie = new SimpleCookie("remember");
	      simpleCookie.setMaxAge(259200);
	      return simpleCookie;
	}
	
	@Bean
	public CookieRememberMeManager rememberMeManager(){
	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	      cookieRememberMeManager.setCookie(rememberMeCookie());
	      cookieRememberMeManager.setCipherKey(Base64.decode("one"));
	      return cookieRememberMeManager;
	}
    
//    /**
//     * 凭证匹配器
//     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
//     * ）
//     * @return
//     */
////    @Bean(name = "credentialsMatcher")
////    public HashedCredentialsMatcher hashedCredentialsMatcher(){
////        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
////
////        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
////        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
////        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
////      	hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
////        return hashedCredentialsMatcher;
////    }
//
//
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
