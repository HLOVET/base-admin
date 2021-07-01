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
@TableName("tb_project_info")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 小红书笔记链接
     */
    private String redbookUrl;

    /**
     * 项目分组
     */
    private String groupName;

    /**
     * 投放费用
     */
    private Float inputMoney;

    /**
     * 跟进人
     */
    private String followPerson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：1-进行中   2-已完结
     */
    private Integer status;

    /**
     * 投放达人id列表(tb_project_star)，  以","分割
     */
    private String putonStars;

    /**
     * 1-已发布   2-未发布
     */
    private Integer releaseFlag;

    /**
     * 总评论
     */
    private Integer commentsCount;

    /**
     * 总点赞
     */
    private Integer likeCount;

    /**
     * 总转发
     */
    private Integer forwardCount;

    /**
     * 项目名称
     */
    private String name;


}
