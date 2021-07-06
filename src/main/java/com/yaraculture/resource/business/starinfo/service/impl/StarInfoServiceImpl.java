package com.yaraculture.resource.business.starinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yaraculture.resource.business.models.req.StarInfoQueReq;
import com.yaraculture.resource.business.models.req.StarInfoReq;
import com.yaraculture.resource.business.models.vo.StarInfoVo;
import com.yaraculture.resource.business.models.vo.StarSimpleInfoVo;
import com.yaraculture.resource.business.starinfo.entity.StarInfo;
import com.yaraculture.resource.business.starinfo.mapper.StarInfoMapper;
import com.yaraculture.resource.business.starinfo.service.StarInfoService;
import com.yaraculture.resource.common.exception.BizException;
import com.yaraculture.resource.common.exception.ErrorCodeEnum;
import com.yaraculture.resource.util.UUIDUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        //昵称重复的不允许添加
        QueryWrapper<StarInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nick_name",starInfoReq.getNickName());
        if (starInfoMapper.selectCount(queryWrapper) > 0){
            throw new BizException(ErrorCodeEnum.STAR_INFO_EXIST.getErrorCode(),"["+starInfoReq.getNickName()+"]已经存在");
        }

        StarInfo starInfo = new StarInfo();
        BeanUtils.copyProperties(starInfoReq, starInfo);
        starInfo.setId(UUIDUtil.getUUID());
        starInfo.setCreateTime(LocalDateTime.now());
        starInfo.setUpdateTime(LocalDateTime.now());
        return starInfoMapper.insert(starInfo);
    }

    @Override
    public int update(StarInfoReq starInfoReq) {
        StarInfo starInfo = new StarInfo();
        BeanUtils.copyProperties(starInfoReq, starInfo);
        starInfo.setUpdateTime(LocalDateTime.now());
        return starInfoMapper.updateById(starInfo);
    }

    @Override
    public Page<StarInfoVo> getPageList(StarInfoQueReq queReq) {
        Page<StarInfoVo> page = new Page<>(queReq.getPageNum(),queReq.getPageSize());
        starInfoMapper.getPageList(page,queReq);
        return page;
    }

    @Override
    public List<StarSimpleInfoVo> getAllStarInfo(String nickName) {
        return starInfoMapper.getAllStarSimpleInfo(nickName);
    }

    @Override
    public boolean batchImport(List<CSVRecord> recordList) {
        List<List<CSVRecord>> partListData = Lists.partition(recordList,30);

        LocalDateTime createTime = LocalDateTime.now();

        partListData.forEach(partList -> {
            //导入的昵称集合 有重复的情况只会导入最后一个
            Set<String> impNickNameSet = partList.stream().map(item -> item.get(0)).collect(Collectors.toSet());
            if (impNickNameSet.size() != partList.size()){
                throw new BizException("EXIST_REPEAT_NAMES","文件中存在重复的昵称数据,请检查哦~~");
            }

            QueryWrapper<StarInfo> wrapper =  new QueryWrapper<>();
            wrapper.select("nick_name").in("nick_name",impNickNameSet);
            List<StarInfo> existNickNameList = starInfoMapper.selectList(wrapper);

            //与库里重复的时候无法导入
            if (CollectionUtils.isNotEmpty(existNickNameList)){
                String notifyStr = existNickNameList.stream().map(StarInfo::getNickName).collect(Collectors.joining(","));
                throw new BizException("EXIST_NAMES","已存在昵称：["+ notifyStr+"],无法导入");
            }
            List<StarInfo> starInfoList =  partList.stream().map(lineData ->{
                StarInfo starInfo = new StarInfo();
                starInfo.setId(UUIDUtil.getUUID());
                starInfo.setNickName(lineData.get("达人昵称"));
                starInfo.setPersonUrl(lineData.get("个人主页链接"));
                starInfo.setAccountLabel(lineData.get("账户标签(多个时用 | 分隔)"));
                starInfo.setFansCount(Integer.parseInt(lineData.get("粉丝数量")));
                starInfo.setNoteCount(Integer.parseInt(lineData.get("笔记数量")));
                starInfo.setLikeCount(Integer.parseInt(lineData.get("点赞收藏总量")));
                starInfo.setAvgLike(Integer.parseInt(lineData.get("平均点赞")));
                starInfo.setContentSharp(Integer.parseInt(lineData.get("内容形式")));
//                starInfo.setAvgCollection(Integer.parseInt(lineData.get("平均收藏")));
//                starInfo.setAvgComment(Integer.parseInt(lineData.get("平均评论")));
                starInfo.setPrice(Integer.parseInt(lineData.get("报价(要求整数)")));
                starInfo.setAccountLevel(lineData.get("账号等级"));
                starInfo.setOwnerName(lineData.get("所属人员"));
                starInfo.setContact(lineData.get("联系方式"));
                starInfo.setCreateTime(createTime);
                starInfo.setUpdateTime(createTime);
                return starInfo;
            }).collect(Collectors.toList());
            //分批次新增
            this.saveBatch(starInfoList);
        });
        return true;
    }

}
