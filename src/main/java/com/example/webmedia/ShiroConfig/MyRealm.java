package com.example.webmedia.ShiroConfig;


import com.example.webmedia.Service.UserService;
import com.example.webmedia.model.VcRole;
import com.example.webmedia.model.VcUser;
import com.example.webmedia.model.VcUserRole;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {


    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        VcUser primaryPrincipal = (VcUser) principalCollection.getPrimaryPrincipal();
        List<VcUserRole> rolesId = primaryPrincipal.getRolesId();
        Set<String> roles  = new HashSet<>();
        if(rolesId!=null)
        {
            for (VcUserRole userRole: rolesId
                    ) {
                VcRole r = userRole.getRoles();
                roles.add(r.getName());
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String name = (String) authenticationToken.getPrincipal();
        Object credentials =  authenticationToken.getCredentials();

        VcUser u = userService.findUserByUsername(name);
        if(u!=null)
        {
            SimpleAuthenticationInfo info = null;
            info = new SimpleAuthenticationInfo(u,u.getPassword(),ByteSource.Util.bytes(name),this.getName());
            return info;
        }
        return null;
    }

}
