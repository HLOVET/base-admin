package com.yaraculture.resource.sys.sysmenu.service;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.common.service.*;
import com.yaraculture.resource.sys.sysmenu.pojo.SysMenu;
import com.yaraculture.resource.sys.sysmenu.vo.SysMenuVo;
import com.yaraculture.resource.common.service.CommonService;

import java.util.List;

public interface SysMenuService extends CommonService<SysMenuVo, SysMenu, String> {
    Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo);
}
