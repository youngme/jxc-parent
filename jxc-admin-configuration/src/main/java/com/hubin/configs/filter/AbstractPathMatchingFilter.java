package com.hubin.configs.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <br>
 *
 * @author hubin
 * @title: 重写过滤链路径匹配规则，增加REST风格的POST、GET、PUT、DELETE
 * @description:
 * @date: 2019/3/22 19:44
 */
public abstract class AbstractPathMatchingFilter extends PathMatchingFilter {

    private static final String DEFAULT_PATH_SEPARATOR = "/";

    public AbstractPathMatchingFilter() {
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }

    /**
     * 重写URL匹配，加入了httpMethod的规则
     * @param request
     * @param path
     * @return
     */
    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String requestURL = this.getPathWithinApplication(request);
        //需要解析出path中的url和httpMethod 例如： path: url==method eg: http://api/menu==GET
        if (requestURL != null && requestURL.endsWith(DEFAULT_PATH_SEPARATOR)) {
            requestURL = requestURL.substring(0, requestURL.length() - 1);
        }
        // path: url==method eg: http://api/menu==GET   需要解析出path中的url和httpMethod
        String[] strings = path.split("==");
        if (strings[0] != null && strings[0].endsWith(DEFAULT_PATH_SEPARATOR)) {
            strings[0] = strings[0].substring(0 , strings[0].length() - 1);
        }
        if (strings.length <= 1) {//表示分割出来的只有URL
            return this.pathsMatch(strings[0], requestURL);
        } else {
            //分割出来URL + httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
            return httpMethod.equalsIgnoreCase(strings[1]) && this.pathsMatch(strings[0], requestURL);
        }

    }

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    protected abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;


    /**
     * 访问失败的拒绝策略
     * @param request
     * @param response
     * @param mapperValue
     * @return
     * @throws Exception
     */
    @Autowired
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mapperValue) throws Exception{
        return this.onAccessDenied(request, response);
    }

    protected abstract boolean onAccessDenied(ServletRequest request, ServletResponse response)throws Exception;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.isAccessAllowed(request, response, mappedValue);
    }


    /**
     * 保存request 请求
     * @param request
     */
    protected void saveRequest(ServletRequest request) {
        WebUtils.saveRequest(request);

    }
}
