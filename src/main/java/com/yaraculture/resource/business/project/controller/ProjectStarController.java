package com.yaraculture.resource.business.project.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.ProjectInfoReq;
import com.yaraculture.resource.business.models.req.ProjectQueReq;
import com.yaraculture.resource.business.models.vo.ProjectInfoVo;
import com.yaraculture.resource.business.models.vo.ProjectStarInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectStar;
import com.yaraculture.resource.business.project.service.ProjectStarService;
import com.yaraculture.resource.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
@RestController
@RequestMapping("/starProject")
public class ProjectStarController {

    @Autowired
    private ProjectStarService projectStarService;

    @GetMapping("/listByProject/{projectId}")
    public ModelAndView pageList(@PathVariable("projectId") String projectId) {
        List<ProjectStarInfoVo> starInfoVoList =  projectStarService.getStarsByProject(projectId);
        return new ModelAndView("business/project/starProjectInfo","starList",starInfoVoList);
    }

    @PostMapping(value = "/update")
    public Result<Boolean> updateInfo(@RequestBody ProjectStarInfoVo req) {
        projectStarService.updateStarInfo(req);
        return Result.of(true);
    }


}

