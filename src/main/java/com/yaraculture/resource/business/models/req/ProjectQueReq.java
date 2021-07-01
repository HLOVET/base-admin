package com.yaraculture.resource.business.models.req;

import com.yaraculture.resource.common.pojo.PageCondition;
import lombok.Data;

@Data
public class ProjectQueReq extends PageCondition {

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
     * 状态：1-进行中   2-已完结
     */
    private Integer status;

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
