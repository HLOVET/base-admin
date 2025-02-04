package com.yaraculture.resource.business.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xieyangli
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_project_star")
public class ProjectStar implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

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
    private Integer likeCount = 0;

    /**
     * 收藏
     */
    private Integer collectCount = 0;

    /**
     * 评论
     */
    private Integer disCounts = 0;

    /**
     * 收藏
     */
    private Integer forwardCount = 0;

    /**
     * 所属项目（tb_project_info.id）
     */
    private String belongProject;

    /**
     * 达人Id
     */
    private String starId;
    /**
     * 笔记链接
     */
    private String noteUrl;


}
