package com.yaraculture.resource.business.starinfo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaraculture.resource.business.models.req.StarInfoQueReq;
import com.yaraculture.resource.business.models.vo.StarInfoVo;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.starinfo.entity.StarInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-28
 */
@Mapper
public interface StarInfoMapper extends BaseMapper<StarInfo> {
    /**
     * 分页查询红人信息
     */
    Page<StarInfoVo> getPageList(Page<StarInfoVo> page, @Param("req") StarInfoQueReq queReq);

    /**
     * 获取全部红人的简略信息
     */
    List<StarSimpleInfoVo> getAllStarSimpleInfo();

}
