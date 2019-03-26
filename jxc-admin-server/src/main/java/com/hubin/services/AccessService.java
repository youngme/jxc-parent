package com.hubin.services;

import com.hubin.domain.system.SysUser;
import com.hubin.dto.system.AccountDTO;

/**
 * <br>
 *
 * @author hubin
 * @title: 登陆访问服务
 * @description:
 * @date: 2019/3/24 10:05
 */
public interface AccessService {

    AccountDTO accessSysUser(String appId);

    AccountDTO accessSysUser(String appId,String password);

    AccountDTO accessUserIsExist(Long uid) ;

    /**
     * 加载用户角色
     * @param appId
     * @return
     */
    String getSysUserRole(String appId);
}
