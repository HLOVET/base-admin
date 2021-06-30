package com.yaraculture.resource.sys.sysshortcutmenu.controller;

import com.yaraculture.resource.common.controller.CommonController;
import com.yaraculture.resource.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import com.yaraculture.resource.sys.sysshortcutmenu.service.SysShortcutMenuService;
import com.yaraculture.resource.sys.sysshortcutmenu.vo.SysShortcutMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/sysShortcutMenu/")
public class SysShortcutMenuController extends CommonController<SysShortcutMenuVo, SysShortcutMenu, String> {
    @Autowired
    private SysShortcutMenuService sysShortcutMenuService;
}
