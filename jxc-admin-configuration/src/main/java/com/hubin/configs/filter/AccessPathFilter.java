package com.hubin.configs.filter;

import com.alibaba.fastjson.JSON;
import com.hubin.utils.RequestResponseUtil;
import com.hubin.utils.ResponseResult;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <br>
 *
 * @author hubin
 * @title: 拦截非权限请求
 * @description:
 * @date: 2019/4/12 12:06
 */
public class AccessPathFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.unAuthor()),response);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

}
