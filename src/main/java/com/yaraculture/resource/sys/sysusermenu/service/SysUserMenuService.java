package com.yaraculture.resource.sys.sysusermenu.service;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.common.service.CommonService;
import com.yaraculture.resource.sys.sysmenu.vo.SysMenuVo;
import com.yaraculture.resource.sys.sysusermenu.pojo.SysUserMenu;
import com.yaraculture.resource.sys.sysusermenu.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, String> {
    Result<List<SysMenuVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String menuIdList);
}
