package com.yaraculture.resource.business.models.vo;


import lombok.Data;


/**
 * 资源简单信息，用于页面展示
 */
@Data
public class StarSimpleInfoVo {
    /**
     * id --> 对应前端value
     */
    private String value;

    /**
     * nickName --> 对应前端title
     */
    private String title;

}
