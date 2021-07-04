package com.yaraculture.resource.business.models.vo;


import lombok.Data;


@Data
public class ProjectStarInfoVo {

    private String id;
    /**
     * 达人基本信息
     */
    private String baseInfo;
    /**
     * 笔记链接
     */
    private String noteUrl;
    /**
     * 垂直领域
     */
    private String verticalArea;

    /**
     * 点赞
     */
    private Integer likeCount;

    /**
     * 收藏
     */
    private Integer collectCount;

    /**
     * 评论
     */
    private Integer disCounts;

    /**
     * 转发
     */
    private Integer forwardCount;

}
