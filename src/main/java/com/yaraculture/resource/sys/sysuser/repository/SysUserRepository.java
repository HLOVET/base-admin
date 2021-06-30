package com.yaraculture.resource.sys.sysuser.repository;

import com.yaraculture.resource.common.repository.*;
import com.yaraculture.resource.sys.sysuser.pojo.SysUser;
import com.yaraculture.resource.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, String> {
    SysUser findByLoginName(String username);
}
