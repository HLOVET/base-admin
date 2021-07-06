package com.yaraculture.resource.business.models.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StarInfoReq {
    /**
     * 账户标签 数组形式
     */
    private String id;

    /**
     * 账户标签 数组形式
     */
    private String accountLabel;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 粉丝数量(以“万”为单位)
     */
    private Integer fansCount;

    /**
     * 笔记数量
     */
    private Integer noteCount;

    /**
     * 点赞收藏总量“万”
     */
    private Integer likeCount;

    /**
     * 点赞平均数
     */
    private Integer avgLike;

    /**
     * 内容形式(1-图文  2-视频)
     */
    private Integer contentSharp;

    /**
     * 报价(“万”为单位)
     */
    private Integer price;

    /**
     * 报价更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime priupdateTime;

    /**
     * 分为 S A B C
     */
    private String accountLevel;

    /**
     * 个人主页链接
     */
    private String personUrl;
    /**
     * 所属人员
     */
    private String ownerName;
    /**
     * 联系方式
     */
    private String contact;
}
