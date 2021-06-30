package com.yaraculture.resource.sys.sysmenu.repository;

import com.yaraculture.resource.common.repository.*;
import com.yaraculture.resource.sys.sysmenu.pojo.SysMenu;
import com.yaraculture.resource.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuRepository extends CommonRepository<SysMenu, String> {
}
