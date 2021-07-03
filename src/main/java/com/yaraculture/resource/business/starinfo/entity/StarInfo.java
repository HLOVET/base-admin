package com.yaraculture.resource.business.starinfo.entity;

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
 * @since 2021-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_star_info")
public class StarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 账户标签 数组形式
     */
    private String accountLabel;

    /**
     * 达人昵称
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
     * 收藏平均数
     */
    private Integer avgCollection;

    /**
     * 评论平均数
     */
    private Integer avgComment;

    /**
     * 内容形式(1-图文  2-视频)
     */
    private Integer contentSharp;

    /**
     * 报价(“万”为单位)
     */
    private Integer price;

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

}
