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
     * 昵称 （模糊搜索）
     */
    private String nickName;

    /**
     * 粉丝数量(以“万”为单位)
     */
    private Integer fansCount;

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

}
