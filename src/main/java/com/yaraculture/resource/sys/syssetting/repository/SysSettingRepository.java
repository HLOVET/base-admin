package com.yaraculture.resource.sys.syssetting.repository;

import com.yaraculture.resource.common.repository.*;
import com.yaraculture.resource.sys.syssetting.pojo.SysSetting;
import com.yaraculture.resource.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysSettingRepository extends CommonRepository<SysSetting, String> {
}
