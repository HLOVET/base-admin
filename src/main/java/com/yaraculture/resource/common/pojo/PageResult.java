package com.yaraculture.resource.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页统一返回对象
 */

@Data
public class PageResult<T> implements Serializable {
    /**
     * 通信数据
     */
    private List<T> data;
    /**
     * 通信状态
     */
    private boolean success = true;
    /**
     * 通信描述
     */
    private String msg = "操作成功";

    /**
     * 数据总量
     */
    private int total;

    /**
     * 通过静态方法获取实例
     */

    public static <T> PageResult<T> of(List<T> data, int total) {
        return new PageResult<T>(data, total);
    }

    private PageResult(List<T> data, int total) {
        this.data = data;
        this.total = total;
    }

}
