package com.yaraculture.resource.business.starinfo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.StarInfoQueReq;
import com.yaraculture.resource.business.models.req.StarInfoReq;
import com.yaraculture.resource.business.models.vo.StarInfoVo;
import com.yaraculture.resource.business.starinfo.entity.StarInfo;
import com.yaraculture.resource.business.starinfo.service.StarInfoService;
import com.yaraculture.resource.common.exception.BizException;
import com.yaraculture.resource.common.exception.ErrorCodeEnum;
import com.yaraculture.resource.common.pojo.PageInfo;
import com.yaraculture.resource.common.pojo.PageResult;
import com.yaraculture.resource.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/starInfo")
public class StarInfoController {

    @Autowired
    private StarInfoService starInfoService;

    @GetMapping("/getAddPage")
    public ModelAndView getAddPage() {
        return new ModelAndView("business/resource/starInfoDetail");
    }

    @GetMapping("/getEditPage")
    public ModelAndView getEditPage(String dataId) {
        StarInfo starInfo =  starInfoService.getById(dataId);
        if (starInfo == null){
            throw BizException.build(ErrorCodeEnum.TARGET_NOT_EXSIT);
        }
        return new ModelAndView("business/resource/starInfoDetailEdit","editStarInfo",starInfo);
    }

    @GetMapping("/pageList/init")
    public ModelAndView pageList() {
        Page<StarInfoVo> infoVoPage = starInfoService.getPageList(new StarInfoQueReq());
        return new ModelAndView("business/resource/starInfo","starInfo",infoVoPage);
    }

    @PostMapping("/pageList")
    public PageResult<StarInfoVo> pageList(StarInfoQueReq queReq) {
        Page<StarInfoVo> infoVoPage = starInfoService.getPageList(queReq);
        PageInfo<StarInfoVo> pageInfo = PageInfo.build(infoVoPage);
        return PageResult.of(pageInfo.getRows(),pageInfo.getTotalRecords());
    }

    @PostMapping(value = "/add")
    public Result<Boolean> addStarInfo(@RequestBody StarInfoReq starInfoReq) {
        starInfoService.insert(starInfoReq);
        return Result.of(true);
    }

    @PostMapping(value = "/update")
    public Result<Boolean> updateStarInfo(@RequestBody StarInfoReq starInfoReq) {
        starInfoService.update(starInfoReq);
        return Result.of(true);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Result<Boolean> updateStarInfo(@PathVariable("id") String id) {
        starInfoService.removeById(id);
        return Result.of(true);
    }


}

