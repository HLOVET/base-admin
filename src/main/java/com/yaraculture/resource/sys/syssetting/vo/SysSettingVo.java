package com.yaraculture.resource.sys.syssetting.vo;

import com.yaraculture.resource.common.pojo.PageCondition;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysSettingVo extends PageCondition implements Serializable {
    private String id;//表id

    private String sysName;//系统名称

    private String sysLogo;//系统logo图标

    private String sysBottomText;//系统底部信息

    private String sysColor;//系统颜色

    private String sysNoticeText;//系统公告

    private String sysApiEncrypt;//API加密 Y/N

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private String userInitPassword;//用户管理：初始、重置密码

}
