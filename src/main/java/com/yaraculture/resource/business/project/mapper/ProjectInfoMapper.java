package com.yaraculture.resource.business.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.ProjectQueReq;
import com.yaraculture.resource.business.models.vo.ProjectInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

    Page<ProjectInfoVo> getPageList(Page<ProjectInfoVo> page, @Param("req") ProjectQueReq queReq);

}
