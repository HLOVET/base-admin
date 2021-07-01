package com.yaraculture.resource.business.project.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.ProjectInfoReq;
import com.yaraculture.resource.business.models.req.ProjectQueReq;
import com.yaraculture.resource.business.models.req.StarInfoQueReq;
import com.yaraculture.resource.business.models.vo.ProjectInfoVo;
import com.yaraculture.resource.business.models.vo.StarInfoVo;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.project.entity.ProjectInfo;
import com.yaraculture.resource.business.project.service.ProjectInfoService;
import com.yaraculture.resource.business.starinfo.service.StarInfoService;
import com.yaraculture.resource.common.exception.BizException;
import com.yaraculture.resource.common.exception.ErrorCodeEnum;
import com.yaraculture.resource.common.pojo.PageInfo;
import com.yaraculture.resource.common.pojo.PageResult;
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
@RequestMapping("/projectInfo")
public class ProjectInfoController {

    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private StarInfoService starInfoService;


    @GetMapping("/list/init")
    public ModelAndView pageList() {
        Page<ProjectInfoVo> infoVoPage = projectInfoService.queryPageInfo(new ProjectQueReq());
        return new ModelAndView("business/project/projectInfo","projectInfo",infoVoPage);
    }

    @GetMapping("/getAddPage")
    public ModelAndView getAddPage() {
        //复用红人资源页面及接口  供项目选中并绑定， 以后数据量超了再优化
        List<StarSimpleInfoVo> starInfoList = starInfoService.getAllStarInfo();
        return new ModelAndView("business/project/projectInfoDetail","starInfoList",starInfoList);
    }

    @GetMapping("/getEditPage")
    public ModelAndView getEditPage(String dataId) {
        ProjectInfo projectInfo =  projectInfoService.getById(dataId);
        if (projectInfo == null){
            throw BizException.build(ErrorCodeEnum.TARGET_NOT_EXSIT);
        }
        ModelAndView modelAndView = new ModelAndView("business/project/projectInfoDetailEdit");
        modelAndView.addObject("projectInfo",projectInfo);

        //返回人员信息，可重新绑定
        List<StarSimpleInfoVo> starInfoList = starInfoService.getAllStarInfo();
        modelAndView.addObject("allStarInfoList",starInfoList);

        return modelAndView;
    }

    @PostMapping("/pageList")
    public PageResult<ProjectInfoVo> pageList(ProjectQueReq queReq) {
        Page<ProjectInfoVo> infoVoPage = projectInfoService.queryPageInfo(queReq);
        PageInfo<ProjectInfoVo> pageInfo = PageInfo.build(infoVoPage);
        return PageResult.of(pageInfo.getRows(),pageInfo.getTotalRecords());
    }

    @PostMapping(value = "/add")
    public Result<Boolean> addInfo(@RequestBody ProjectInfoReq projectInfoReq) {
        projectInfoService.insert(projectInfoReq);
        return Result.of(true);
    }

    @PostMapping(value = "/update")
    public Result<Boolean> updateInfo(@RequestBody ProjectInfoReq projectInfoReq) {
        projectInfoService.update(projectInfoReq);
        return Result.of(true);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Result<Boolean> updateInfo(@PathVariable("id") String id) {
        projectInfoService.delete(id);
        return Result.of(true);
    }



}

