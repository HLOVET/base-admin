package com.yaraculture.resource.business.starinfo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaraculture.resource.business.models.req.StarInfoQueReq;
import com.yaraculture.resource.business.models.req.StarInfoReq;
import com.yaraculture.resource.business.models.vo.StarInfoVo;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.starinfo.entity.StarInfo;
import com.yaraculture.resource.business.starinfo.mapper.StarInfoMapper;
import com.yaraculture.resource.business.starinfo.service.StarInfoService;
import com.yaraculture.resource.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-28
 */
@Service
public class StarInfoServiceImpl extends ServiceImpl<StarInfoMapper, StarInfo> implements StarInfoService {

    @Autowired
    private StarInfoMapper starInfoMapper;

    @Override
    public int insert(StarInfoReq starInfoReq) {
        StarInfo starInfo = new StarInfo();
        BeanUtils.copyProperties(starInfoReq, starInfo);
        starInfo.setId(UUIDUtil.getUUID());
        return starInfoMapper.insert(starInfo);
    }

    @Override
    public int update(StarInfoReq starInfoReq) {
        StarInfo starInfo = new StarInfo();
        BeanUtils.copyProperties(starInfoReq, starInfo);
        return starInfoMapper.updateById(starInfo);
    }

    @Override
    public Page<StarInfoVo> getPageList(StarInfoQueReq queReq) {
        Page<StarInfoVo> page = new Page<>(queReq.getPageNum(),queReq.getPageSize());
        starInfoMapper.getPageList(page,queReq);
        return page;
    }

    @Override
    public List<StarSimpleInfoVo> getAllStarInfo() {
        return starInfoMapper.getAllStarSimpleInfo();
    }
}
