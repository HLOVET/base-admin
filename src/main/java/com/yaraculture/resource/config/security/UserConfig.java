package com.yaraculture.resource.config.security;

import com.yaraculture.resource.sys.sysuser.service.SysUserService;
import com.yaraculture.resource.sys.sysuser.vo.SysUserVo;
import com.yaraculture.resource.sys.sysuserauthority.service.SysUserAuthorityService;
import com.yaraculture.resource.sys.sysuserauthority.vo.SysUserAuthorityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class UserConfig implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserAuthorityService sysUserAuthorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        SysUserVo sysUserVo = sysUserService.findByLoginName(username).getData();
        //查询权限
        List<SysUserAuthorityVo> sysUserAuthorityVoList = sysUserAuthorityService.findByUserId(sysUserVo.getUserId()).getData();
        StringBuilder authorityList = new StringBuilder();
        for (int i = 0; i < sysUserAuthorityVoList.size(); i++) {
            SysUserAuthorityVo sysUserAuthorityVo = sysUserAuthorityVoList.get(i);
            authorityList.append(sysUserAuthorityVo.getSysAuthority().getAuthorityName());
            if (i != sysUserAuthorityVoList.size() - 1) {
                authorityList.append(",");
            }
        }

        //查无此用户
        if(StringUtils.isEmpty(sysUserVo.getUserId())){
            sysUserVo.setLoginName("查无此用户");
            sysUserVo.setPassword("查无此用户");
        }

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        return new User(sysUserVo.getLoginName(), sysUserVo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(authorityList.toString()));
    }
}
