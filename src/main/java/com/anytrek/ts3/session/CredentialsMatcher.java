package com.anytrek.ts3.session;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.context.annotation.Bean;

import com.anytrek.util.PasswordUtil;
 
/**
 * 自定义密码比较规则
 * @author Administrator
 *
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{
	
	
    @Override
    @Bean(name = "credentialsMatcher")
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //获得用户输入的密码:
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        boolean flag = PasswordUtil.verify(inPassword, dbPassword);
        return flag;
    }
}
