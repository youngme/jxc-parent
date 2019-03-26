package com.hubin.configs.shiro;


import com.hubin.configs.filter.RestShiroFilterFactoryBean;
import com.hubin.configs.filter.ShiroFilterChainManager;
import com.hubin.configs.filter.StatelessWebSubjectFactory;
import com.hubin.configs.realm.AonModularRealmAuthenticator;
import com.hubin.configs.realm.RealmManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <br>
 *
 * @author hubin
 * @title:Shiro安全配置项
 * @description:
 * @date: 2019/3/14 14:10
 */
@Configuration
public class ShiroConfiguration {

//    @Autowired
//    SysUserService sysUserService;
//
//    @Autowired
//    StringRedisTemplate redisTemplate;
//
//    @Autowired
//    SysResourceService sysResourceService;
//
//    @Autowired
//    AccessService accessService;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainManager filterChainManager) {
        RestShiroFilterFactoryBean shiroFilterFactoryBean = new RestShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setFilters(filterChainManager.initGetFilters());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainManager.initGetFilterChain());
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(RealmManager realmManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(new AonModularRealmAuthenticator());
        securityManager.setRealms(realmManager.initGetRealm());

        // 无状态subjectFactory设置
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(Boolean.FALSE);
        StatelessWebSubjectFactory subjectFactory = new StatelessWebSubjectFactory();
        securityManager.setSubjectFactory(subjectFactory);

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

//    @Bean
//    public RealmManager realmManager() {
//        return new RealmManager(passwordMatcher(), jwtMatcher(),accessService);
//    }
//
//
//
//    @Bean
//    public PasswordMatcher passwordMatcher() {
//        return new PasswordMatcher();
//    }
//
//    @Bean
//    public JwtMatcher jwtMatcher() {
//        return new JwtMatcher();
//    }
//
//    @Bean
//    public ShiroFilterManager shiroFilterManager(StringRedisTemplate redisTemplate) {
//        return new ShiroFilterManager(redisTemplate,sysResourceService,accessService);
//    }
//
//
//    @Bean("lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }


}
