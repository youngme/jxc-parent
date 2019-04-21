package com.hubin.configs.realm;

import com.hubin.configs.matcher.JwtMatcher;
import com.hubin.configs.matcher.PasswordMatcher;
import com.hubin.configs.token.JwtToken;
import com.hubin.configs.token.PasswordToken;
import com.hubin.services.system.AccessService;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <br>
 * realm管理器
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/22 21:31
 */
@Component
public class RealmManager {
    private PasswordMatcher passwordMatcher;
    private JwtMatcher jwtMatcher;
    private AccessService accessService;

    @Autowired
    public RealmManager(PasswordMatcher passwordMatcher, JwtMatcher jwtMatcher, AccessService accessService) {
        this.passwordMatcher = passwordMatcher;
        this.jwtMatcher = jwtMatcher;
        this.accessService = accessService;
    }

    public List<Realm> initGetRealm() {
        List<Realm> realmList = new LinkedList<>();
        // ----- password
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setAccessService(accessService);
        passwordRealm.setCredentialsMatcher(passwordMatcher);
        passwordRealm.setAuthenticationTokenClass(PasswordToken.class);
        realmList.add(passwordRealm);
        // ----- jwt
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(jwtMatcher);
        jwtRealm.setAuthenticationTokenClass(JwtToken.class);
        realmList.add(jwtRealm);
        return Collections.unmodifiableList(realmList);
    }
}
