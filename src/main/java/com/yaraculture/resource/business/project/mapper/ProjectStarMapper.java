package com.yaraculture.resource.business.project.mapper;

import com.yaraculture.resource.business.models.vo.ProjectStarInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectStar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
@Mapper
public interface ProjectStarMapper extends BaseMapper<ProjectStar> {

    /**
     * 获取项目人员信息
     */
    List<ProjectStarInfoVo> getStarsByProject(String projectId);

}
