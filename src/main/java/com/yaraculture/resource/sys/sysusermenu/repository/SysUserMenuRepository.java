package com.yaraculture.resource.sys.sysusermenu.repository;

import com.yaraculture.resource.common.repository.CommonRepository;
import com.yaraculture.resource.sys.sysusermenu.pojo.SysUserMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMenuRepository extends CommonRepository<SysUserMenu, String> {
    List<SysUserMenu> findByUserId(String userId);
}
