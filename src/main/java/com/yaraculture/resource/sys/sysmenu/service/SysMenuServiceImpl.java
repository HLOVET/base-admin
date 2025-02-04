package com.yaraculture.resource.sys.sysmenu.service;

import com.yaraculture.resource.common.pojo.Result;
import com.yaraculture.resource.common.service.CommonServiceImpl;
import com.yaraculture.resource.sys.sysmenu.pojo.SysMenu;
import com.yaraculture.resource.sys.sysmenu.repository.SysMenuRepository;
import com.yaraculture.resource.sys.sysmenu.vo.SysMenuVo;
import com.yaraculture.resource.sys.sysusermenu.service.SysUserMenuService;
import com.yaraculture.resource.sys.sysusermenu.vo.SysUserMenuVo;
import com.yaraculture.resource.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SysMenuServiceImpl extends CommonServiceImpl<SysMenuVo, SysMenu, String> implements SysMenuService{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Autowired
    private SysUserMenuService sysUserMenuService;

    @Override
    public Result<String> delete(String id) {
        //先删除子节点
        SysMenuVo sysMenuVo = new SysMenuVo();
        sysMenuVo.setMenuParentId(id);
        super.list(sysMenuVo).getData().forEach((menuVo)->{
            super.delete(menuVo.getMenuId());
        });

        //后删除所有用户菜单表关联信息
        SysUserMenuVo sysUserMenuVo = new SysUserMenuVo();
        sysUserMenuVo.setMenuId(id);
        sysUserMenuService.list(sysUserMenuVo).getData().forEach((vo)->{
            sysUserMenuService.delete(vo.getUserMenuId());
        });

        //再删除自己
        return super.delete(id);
    }

    @Override
    public Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo) {
        List<SysMenuVo> sysMenuVoList = super.list(entityVo).getData();
        return Result.of(MenuUtil.getChildBySysMenuVo("",sysMenuVoList));
    }
}
