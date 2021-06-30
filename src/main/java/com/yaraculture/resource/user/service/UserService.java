package com.yaraculture.resource.user.service;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.sys.sysuser.vo.SysUserVo;

public interface UserService {
    Result<SysUserVo> updatePassword(String oldPassword, String newPassword);

    Result<SysUserVo> updateUser(SysUserVo sysUserVo);
}
