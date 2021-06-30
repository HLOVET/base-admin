package com.yaraculture.resource.sys.sysuser.controller;

import com.yaraculture.resource.annotation.Decrypt;
import com.yaraculture.resource.annotation.Encrypt;
import com.yaraculture.resource.common.controller.CommonController;
import com.yaraculture.resource.common.pojo.PageInfo;
import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.sys.sysuser.pojo.SysUser;
import com.yaraculture.resource.sys.sysuser.service.SysUserService;
import com.yaraculture.resource.sys.sysuser.vo.SysUserVo;
import com.yaraculture.resource.util.SecurityUtil;
import com.yaraculture.resource.util.SysSettingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sys/sysUser/")
public class SysUserController extends CommonController<SysUserVo, SysUser, String> {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping("user")
    public ModelAndView user(){
        return new ModelAndView("sys/user/user","initPassword", SysSettingUtil.getSysSetting().getUserInitPassword());
    }

    @PostMapping("resetPassword")
    @Decrypt
    @Encrypt
    public Result<SysUserVo> resetPassword(SysUserVo sysUserVo){
        return sysUserService.resetPassword(sysUserVo.getUserId());
    }

    @PostMapping("pageOnLine")
    @Decrypt
    @Encrypt
    public Result<PageInfo<SysUserVo>> pageOnLine(SysUserVo sysUserVo){
        ArrayList<SysUserVo> sysUserVoList = new ArrayList<>();
        List<Object> allPrincipals = securityUtil.sessionRegistryGetAllPrincipals();
        for (Object allPrincipal : allPrincipals) {
            SysUserVo userVo = new SysUserVo();
            User user = (User) allPrincipal;
            userVo.setLoginName(user.getUsername());
            sysUserVoList.add(userVo);
        }
        PageInfo<SysUserVo> pageInfo = new PageInfo<>();
        pageInfo.setPage(1);//页码
        pageInfo.setPageSize(10000);//页面大小
        pageInfo.setRows(sysUserVoList);//分页结果
        pageInfo.setTotalRecords(sysUserVoList.size());//总记录数
        pageInfo.setTotalPage(1);//总页数
        return Result.of(pageInfo);
    }

    @DeleteMapping("forced/{loginName}")
    public Result<String> forced( @PathVariable("loginName") String loginName) {
        securityUtil.sessionRegistryRemoveUserByLoginName(loginName);
        return Result.of("操作成功");
    }
}
