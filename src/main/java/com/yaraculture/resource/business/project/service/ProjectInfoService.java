package com.yaraculture.resource.business.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.ProjectInfoReq;
import com.yaraculture.resource.business.models.req.ProjectQueReq;
import com.yaraculture.resource.business.models.req.StarInfoReq;
import com.yaraculture.resource.business.models.vo.ProjectInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
public interface ProjectInfoService extends IService<ProjectInfo> {

    /**
     * 项目信息列表
     */
    Page<ProjectInfoVo> queryPageInfo(ProjectQueReq queReq);

    /**
     * 新增项目信息,同时新增相应的人员信息
     */
    int insert(ProjectInfoReq projectInfoReq);

    /**
     * 更新项目信息
     */
    int update(ProjectInfoReq projectInfoReq);

    /**
     * 删除项目信息及关联人员（不会删除人员原始信息）
     */
    void delete(String dataId);


}
