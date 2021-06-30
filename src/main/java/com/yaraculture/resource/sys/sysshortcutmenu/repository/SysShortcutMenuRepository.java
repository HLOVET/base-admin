package com.yaraculture.resource.sys.sysshortcutmenu.repository;

import com.yaraculture.resource.common.repository.CommonRepository;
import com.yaraculture.resource.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysShortcutMenuRepository extends CommonRepository<SysShortcutMenu, String> {
    List<SysShortcutMenu> findByUserId(String userId);
}
