package com.yaraculture.resource.business.models.req;

import com.yaraculture.resource.common.pojo.PageCondition;
import lombok.Data;

@Data
public class StarInfoQueReq extends PageCondition {

    /**
     * 账户标签 数组形式
     */
    private String accountLabel;
    /**
     * 账号等级
     */
    private String accountLevel;

    /**
     * 昵称 （模糊搜索）
     */
    private String nickName;

    /**
     * 粉丝数量(以“万”为单位)
     */
    private Integer fansCount;

    /**
     * 所属人员
     */
    private String ownerName;
}
