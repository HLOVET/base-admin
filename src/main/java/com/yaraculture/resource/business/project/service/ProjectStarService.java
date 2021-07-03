package com.yaraculture.resource.business.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaraculture.resource.business.models.vo.ProjectStarInfoVo;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectStar;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
public interface ProjectStarService extends IService<ProjectStar> {

    /**
     * 根据项目批量新增
     */
    boolean batchInsertWithProject(List<StarSimpleInfoVo> starList, String projectId);

    /**
     * 批量删除项目关联人员
     */
    boolean batchDeleteWithProjectId(String projectId);

    /**
     * 获取项目所属红人信息
     */
    List<ProjectStarInfoVo> getStarsByProject(String projectId);

    /**
     * 更新项目人员信息
     */
    boolean updateStarInfo(ProjectStarInfoVo req);

}
