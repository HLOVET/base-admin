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
import com.yaraculture.resource.util.CsvImportUtil;
import com.yaraculture.resource.util.DateUtil;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

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
        StarInfoVo starInfoVo = new StarInfoVo();
        BeanUtils.copyProperties(starInfo, starInfoVo);
        starInfoVo.setPriupdateTimeStr(DateUtil.formatWithoutT(starInfo.getPriupdateTime()));
        return new ModelAndView("business/resource/starInfoDetailEdit","editStarInfo",starInfoVo);
    }

    @GetMapping("/pageList/init")
    public ModelAndView pageList() {
        Page<StarInfoVo> infoVoPage = starInfoService.getPageList(new StarInfoQueReq());
        return new ModelAndView("business/resource/starInfo","starInfo",infoVoPage);
    }

    @GetMapping("/admin/pageList/init")
    public ModelAndView getAdminPageList() {
        Page<StarInfoVo> infoVoPage = starInfoService.getPageList(new StarInfoQueReq());
        return new ModelAndView("business/resource/starInfoAdmin","starInfo",infoVoPage);
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

    @PostMapping(value = "/importFile")
    public Result<Boolean> batchImportInfo(@RequestParam MultipartFile file ) {
        // 使用CSV工具类，生成file文件
        File csvFile = CsvImportUtil.uploadFile(file);

        String[] FILE_HEADER = {"达人昵称","个人主页链接","账户标签(多个时用 | 分隔)","粉丝数量","点赞收藏总量","平均点赞"
                ,"内容形式","报价(要求整数)","账号等级","所属人员","联系方式"};
        List<CSVRecord> csvRecordList = CsvImportUtil.readCSV(csvFile, FILE_HEADER);
        if (csvRecordList.size() > 100){
            throw BizException.build(ErrorCodeEnum.IMPORT_FILE_OVERSIZE);
        }
        starInfoService.batchImport(csvRecordList);
        // 删除文件
        csvFile.delete();
        return Result.of(true);
    }


    //TODO  暂时不开放删除功能 后面权限粒度做的细了再考虑
//    @DeleteMapping(value = "/delete/{id}")
//    public Result<Boolean> updateStarInfo(@PathVariable("id") String id) {
//        starInfoService.removeById(id);
//        return Result.of(true);
//    }


}

