package com.yaraculture.resource.sys.sysauthority.repository;

import com.yaraculture.resource.common.repository.*;
import com.yaraculture.resource.sys.sysauthority.pojo.SysAuthority;
import com.yaraculture.resource.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAuthorityRepository extends CommonRepository<SysAuthority, String> {
}
