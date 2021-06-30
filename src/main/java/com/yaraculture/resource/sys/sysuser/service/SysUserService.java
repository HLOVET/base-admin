package com.yaraculture.resource.sys.sysuser.service;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.common.service.CommonService;
import com.yaraculture.resource.sys.sysuser.pojo.SysUser;
import com.yaraculture.resource.sys.sysuser.vo.SysUserVo;

public interface SysUserService extends CommonService<SysUserVo, SysUser, String> {
    Result<SysUserVo> findByLoginName(String username);
    Result<SysUserVo> resetPassword(String userId);
}
