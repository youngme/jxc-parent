package com.hubin.services.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysUser;
import com.hubin.dto.system.AccountDTO;
import com.hubin.factor.system.SysUserFactor;
import com.hubin.services.system.AccessService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <br>
 *
 * @author hubin
 * @title: 登陆访问
 * @description:
 * @date: 2019/3/24 10:15
 */
@Service
public class IAccessService extends ServiceImpl<SysUserFactor, SysUser> implements AccessService {

    /**
     * 根据用户的标示获取信息
     * @param appId
     * @return
     */
    @Override
    public AccountDTO accessSysUser(String appId) {
        SysUser user = baseMapper.selectByAppId(appId);
        return Objects.isNull(user)?null:new AccountDTO(user.getUid(),user.getUsername(),user.getPassword(),user.getSalt());
    }

    @Override
    public AccountDTO accessSysUser(String appId, String password) {
        SysUser user = baseMapper.selectByUserInfo(appId, password);
        return Objects.isNull(user)?null:new AccountDTO(user.getUid(),user.getUsername(),user.getPassword(),user.getSalt());
    }

    @Override
    public AccountDTO accessUserIsExist(Long uid) {
        SysUser user = baseMapper.selectById(uid);
        return Objects.isNull(user)?null:new AccountDTO(user.getUid(),user.getUsername(),user.getPassword(),user.getSalt());
    }

    @Override
    public String getSysUserRole(String appId) {
        return baseMapper.selectUserRoles(appId);
    }
}
