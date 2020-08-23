package com.qin.config;

import com.qin.entity.SysPermission;
import com.qin.entity.SysRole;
import com.qin.entity.UserInfo;
import com.qin.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (SysRole role : userInfo.getRoles()) {
            info.addRole(role.getName());
            for (SysPermission permission : role.getPermissions()) {
                info.addStringPermission(permission.getName());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getPrincipal());

        UserInfo userInfo = userInfoService.findByUsername(userName);
        if (userInfo == null) {
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSlat()),
                getName()
        );
        return info;
    }
}
