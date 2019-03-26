package com.hubin.configs.realm;

import com.hubin.configs.token.JwtToken;
import com.hubin.utils.JwtUtils;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Map;
import java.util.Set;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/22 21:33
 */
public class JwtRealm extends AuthorizingRealm {
    private static final String JWT = "jwt:";
    private static final int NUM_4 = 4;
    private static final char LEFT = '{';
    private static final char RIGHT = '}';

    @Override
    public Class<?> getAuthenticationTokenClass() {
        // 此realm只支持jwtToken
        return JwtToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String payload = (String) principalCollection.getPrimaryPrincipal();
        // likely to be json, parse it:
        if (payload.startsWith(JWT) && payload.charAt(NUM_4) == LEFT
                && payload.charAt(payload.length() - 1) == RIGHT) {

            Map<String, Object> payloadMap = JwtUtils.readValue(payload.substring(4));
            Set<String> roles = JwtUtils.split((String)payloadMap.get("roles"));
            Set<String> permissions = JwtUtils.split((String)payloadMap.get("perms"));
            SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
            if(null!=roles&&!roles.isEmpty()) {
                info.setRoles(roles);
            }
            if(null!=permissions&&!permissions.isEmpty()) {
                info.setStringPermissions(permissions);
            }
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        JwtToken jwtToken = (JwtToken)authenticationToken;
        String jwt = (String)jwtToken.getCredentials();
        String payload = null;
        try{
            // 预先解析Payload
            // 没有做任何的签名校验
            payload = JwtUtils.parseJwtPayload(jwt);
        } catch(MalformedJwtException e){
            //令牌格式错误
            throw new AuthenticationException("errJwt");
        } catch(Exception e){
            //令牌无效
            throw new AuthenticationException("errsJwt");
        }
        if(null == payload){
            //令牌无效
            throw new AuthenticationException("errJwt");
        }
        return new SimpleAuthenticationInfo("jwt:"+payload,jwt,this.getName());
    }
}