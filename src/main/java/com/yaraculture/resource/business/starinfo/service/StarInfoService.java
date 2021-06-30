package com.yaraculture.resource.business.starinfo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.StarInfoQueReq;
import com.yaraculture.resource.business.models.req.StarInfoReq;
import com.yaraculture.resource.business.models.vo.StarInfoVo;
import com.yaraculture.resource.business.starinfo.entity.StarInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-28
 */
public interface StarInfoService extends IService<StarInfo> {

    /**
     * 新增红人资源信息
     */
    int insert(StarInfoReq starInfoReq);

    /**
     * 更新红人资源信息
     */
    int update(StarInfoReq starInfoReq);

    /**
     * 分页查询红人信息
     */
    Page<StarInfoVo> getPageList(StarInfoQueReq queReq);

}
