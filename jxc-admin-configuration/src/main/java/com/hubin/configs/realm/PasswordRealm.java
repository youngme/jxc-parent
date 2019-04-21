package com.hubin.configs.realm;

import com.hubin.configs.token.PasswordToken;
import com.hubin.dto.system.AccountDTO;
import com.hubin.services.system.AccessService;
import com.hubin.utils.ShiroEnctryption;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <br>
 * 这里是登录认证realm
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/22 21:34
 */
public class PasswordRealm extends AuthorizingRealm {

    private AccessService accessService;


    /**
     * description 此Realm只支持PasswordToken
     *
     * @return java.lang.Class<?>
     */
    @Override
    public Class<?> getAuthenticationTokenClass() {
        return PasswordToken.class;
    }


    /**
     * description 这里只需要认证登录，成功之后派发 json web token 授权在那里进行
     *
     * @param principalCollection 1
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof PasswordToken)) {
            return null;
        }

        if(null==authenticationToken.getPrincipal()||null==authenticationToken.getCredentials()){
            throw new UnknownAccountException();
        }
        String appId = (String)authenticationToken.getPrincipal();
        AccountDTO account = accessService.accessSysUser(appId);
        if (account != null) {
            // 用盐对密码进行加密
            ((PasswordToken) authenticationToken).setPassword(ShiroEnctryption.getPwdShaHash(((PasswordToken) authenticationToken).getPassword()));
            return new SimpleAuthenticationInfo(appId,account.getPassword(),getName());
        } else {
            return new SimpleAuthenticationInfo(appId,"",getName());
        }

    }

    public void setAccessService(AccessService accessService) {
        this.accessService = accessService;
    }
}
