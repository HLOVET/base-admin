package com.yaraculture.resource.sys.sysuserauthority.repository;

import com.yaraculture.resource.common.repository.CommonRepository;
import com.yaraculture.resource.sys.sysuserauthority.pojo.SysUserAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserAuthorityRepository extends CommonRepository<SysUserAuthority, String> {
    List<SysUserAuthority> findByUserId(String userId);
}
