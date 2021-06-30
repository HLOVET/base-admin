package com.yaraculture.resource.sys.sysshortcutmenu.service;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.common.service.CommonService;
import com.yaraculture.resource.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import com.yaraculture.resource.sys.sysshortcutmenu.vo.SysShortcutMenuVo;

import java.util.List;

public interface SysShortcutMenuService extends CommonService<SysShortcutMenuVo, SysShortcutMenu, String> {
    Result<List<SysShortcutMenuVo>> findByUserId(String userId);
}
