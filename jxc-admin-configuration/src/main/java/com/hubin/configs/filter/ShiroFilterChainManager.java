package com.hubin.configs.filter;

import com.hubin.configs.support.RolePermSupport;
import com.hubin.configs.support.SpringContextHolder;
import com.hubin.domain.system.SysRolePermission;
import com.hubin.services.AccessService;
import com.hubin.services.SysResourceService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>
 *
 * @author hubin
 * @title: Filter 管理器
 * @description:
 * @date: 2019/3/22 20:55
 */
@Component
public class ShiroFilterChainManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroFilterChainManager.class);

//    private final ShiroFilterRulesProvider shiroFilterRulesProvider;
    private final StringRedisTemplate redisTemplate;
    private final SysResourceService sysResourceService;
    private final AccessService accessService;

    @Autowired
    public ShiroFilterChainManager(StringRedisTemplate redisTemplate, SysResourceService sysResourceService,
                                   AccessService accessService) {
        this.redisTemplate = redisTemplate;
        this.sysResourceService = sysResourceService;
        this.accessService = accessService;
    }

    /**
     * description 初始化获取过滤链
     *
     * @return java.util.Map<java.lang.String,javax.servlet.Filter>
     */
    public Map<String, Filter> initGetFilters() {
        Map<String,Filter> filters = new LinkedHashMap<>();
        PasswordFilter passwordFilter = new PasswordFilter();
        passwordFilter.setRedisTemplate(redisTemplate);
        filters.put("auth",passwordFilter);
        JwtFilter jwtFilter = new JwtFilter(redisTemplate,accessService);
        jwtFilter.setAccountService(accessService);
        filters.put("jwt",jwtFilter);
        return filters;
    }
    /**
     * description 初始化获取过滤链规则
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public Map<String,String> initGetFilterChain() {
        Map<String,String> filterChain = new LinkedHashMap<>();
        // -------------anon 默认过滤器忽略的URL
        List<String> defalutAnon = Arrays.asList("/css/**","/js/**");
        defalutAnon.forEach(ignored -> filterChain.put(ignored,"anon"));
        // -------------auth 默认需要认证过滤器的URL 走auth--PasswordFilter
        List<String> defalutAuth = Arrays.asList("/account/**");
        defalutAuth.forEach(auth -> filterChain.put(auth,"auth"));
        // -------------dynamic 动态URL
        if (sysResourceService != null) {
            List<SysRolePermission> rolePermRules = this.sysResourceService.selectRoleRules();
            if (null != rolePermRules) {
                rolePermRules.forEach(rule -> {
                    StringBuilder chain = RolePermSupport.toFilterChain(rule);
                    if (null != chain) {
                        filterChain.putIfAbsent(rule.getUrl(),chain.toString());
                    }
                });
            }
        }
        return filterChain;
    }
    /**
     * description 动态重新加载过滤链规则
     */
    public void reloadFilterChain() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        AbstractShiroFilter abstractShiroFilter = null;
        try {
            abstractShiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
            RestPathMatchingFilterChainResolver filterChainResolver = (RestPathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
            filterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(this.initGetFilterChain());
            shiroFilterFactoryBean.getFilterChainDefinitionMap().forEach((k,v) -> filterChainManager.createChain(k,v));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
