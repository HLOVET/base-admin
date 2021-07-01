package com.yaraculture.resource.business.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.ProjectInfoReq;
import com.yaraculture.resource.business.models.req.ProjectQueReq;
import com.yaraculture.resource.business.models.req.StarInfoReq;
import com.yaraculture.resource.business.models.vo.ProjectInfoVo;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectInfo;
import com.yaraculture.resource.business.project.entity.ProjectStar;
import com.yaraculture.resource.business.project.mapper.ProjectInfoMapper;
import com.yaraculture.resource.business.project.service.ProjectInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaraculture.resource.business.project.service.ProjectStarService;
import com.yaraculture.resource.business.starinfo.entity.StarInfo;
import com.yaraculture.resource.util.JsonUtil;
import com.yaraculture.resource.util.UUIDUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements ProjectInfoService {

    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private ProjectStarService projectStarService;


    @Override
    public Page<ProjectInfoVo> queryPageInfo(ProjectQueReq queReq) {
        Page<ProjectInfoVo> page = new Page<>(queReq.getPageNum(),queReq.getPageSize());
        projectInfoMapper.getPageList(page,queReq);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ProjectInfoReq projectInfoReq) {
        String projectId = UUIDUtil.getUUID();
        ProjectInfo projectInfo = new ProjectInfo();
        BeanUtils.copyProperties(projectInfoReq, projectInfo);
        //先复制属性 再设置特殊值
        projectInfo.setId(projectId);

        if (CollectionUtils.isNotEmpty(projectInfoReq.getSelectPersons())) {
            //关联的红人资源冗余一份名字
            String starPersonNames= StringUtils.join(projectInfoReq.getSelectPersons().stream().map(StarSimpleInfoVo::getTitle).collect(Collectors.toList()),",");
            projectInfo.setPutonStars(starPersonNames);
            //同时保存项目关联的红人资源
            projectStarService.batchInsertWithProject(projectInfoReq.getSelectPersons(), projectId);
        }
        return projectInfoMapper.insert(projectInfo);
    }

    @Override
    @Transactional
    public int update(ProjectInfoReq projectInfoReq) {
        ProjectInfo projectInfo = new ProjectInfo();
        BeanUtils.copyProperties(projectInfoReq, projectInfo);
        if (CollectionUtils.isNotEmpty(projectInfoReq.getSelectPersons())) {
            String starPersonNames= StringUtils.join(projectInfoReq.getSelectPersons().stream().map(StarSimpleInfoVo::getTitle).collect(Collectors.toList()),",");
            projectInfo.setPutonStars(starPersonNames);

            //项目关联的红人资源 先删后加
            projectStarService.batchDeleteWithProjectId(projectInfoReq.getId());
            projectStarService.batchInsertWithProject(projectInfoReq.getSelectPersons(), projectInfoReq.getId());
        }
        return projectInfoMapper.updateById(projectInfo);
    }

    @Override
    @Transactional
    public void delete(String dataId) {
        this.removeById(dataId);
        //删除相应的红人资源信息
        projectStarService.batchDeleteWithProjectId(dataId);
    }



}
