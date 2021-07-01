package com.yaraculture.resource.business.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectStar;
import com.yaraculture.resource.business.project.mapper.ProjectStarMapper;
import com.yaraculture.resource.business.project.service.ProjectStarService;
import com.yaraculture.resource.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProjectStarServiceImpl extends ServiceImpl<ProjectStarMapper, ProjectStar> implements ProjectStarService {

    @Autowired
    private ProjectStarMapper projectStarMapper;

    @Override
    public boolean batchInsertWithProject(List<StarSimpleInfoVo> starList, String projectId) {
        List<ProjectStar> projectStarList = starList.stream().map(item -> {
            ProjectStar projectStar = new ProjectStar();
            projectStar.setId(UUIDUtil.getUUID());
            projectStar.setBelongProject(projectId);
            projectStar.setStarId(item.getValue());
            projectStar.setBaseInfo(item.getTitle());
            return projectStar;
        }).collect(Collectors.toList());
        return this.saveBatch(projectStarList);
    }

    @Override
    public boolean batchDeleteWithProjectId(String projectId) {
        QueryWrapper<ProjectStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_project",projectId);
        return this.remove(queryWrapper);
    }
}
