package com.example.webmedia.ShiroConfig;


import com.example.webmedia.Service.loginService;
import com.example.webmedia.model.Consumer;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {


    @Autowired
    loginService lg;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Consumer primaryPrincipal = (Consumer) principalCollection.getPrimaryPrincipal();
        Set<String> roles  = new HashSet<>();
        roles.add(primaryPrincipal.getIdentity());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("inini");
        String name = (String) authenticationToken.getPrincipal();
        Object credentials =  authenticationToken.getCredentials();
        Consumer u = lg.QuerybyName(name);
        System.out.println("Consumer==="+u.getUsername()+"    "+u.getPassword());
        if(u!=null)
        {
            SimpleAuthenticationInfo info = null;
            info = new SimpleAuthenticationInfo(u,u.getPassword(),ByteSource.Util.bytes(name),this.getName());
            return info;
        }
        return null;
    }


}
