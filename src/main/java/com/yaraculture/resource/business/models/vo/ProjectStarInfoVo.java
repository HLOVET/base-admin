package com.yaraculture.resource.business.models.vo;


import lombok.Data;


@Data
public class ProjectStarInfoVo {

    private Integer id;
    /**
     * 达人基本信息
     */
    private String baseInfo;

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
    private Integer commentsCount;

    /**
     * 收藏
     */
    private Integer forwardCount;

}
