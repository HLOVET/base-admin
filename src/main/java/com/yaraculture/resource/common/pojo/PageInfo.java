package com.yaraculture.resource.common.pojo;

import com.yaraculture.resource.util.CopyUtil;
import lombok.Data;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * 分页对象（参考JqGrid插件）
 */
@Data
public class PageInfo<M> {
    private int page;//当前页码
    private int pageSize;//页面大小

    private List<M> rows;//分页结果
    private int totalRecords;//总记录数
    private int totalPage;//总页数

    /**
     * 获取统一分页结果
     */
    public static <M> PageInfo<M> of(Page page, Class<M> entityModelClass) {
        int records = (int) page.getTotalElements();
        int pageSize = page.getSize();
        int total = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;

        PageInfo<M> pageInfo = new PageInfo<>();
        pageInfo.setPage(page.getNumber() + 1);//页码
        pageInfo.setPageSize(pageSize);//页面大小
        pageInfo.setRows(CopyUtil.copyList(page.getContent(), entityModelClass));//分页结果
        pageInfo.setTotalRecords(records);//总记录数
        pageInfo.setTotalPage(total);//总页数
        return pageInfo;
    }

    /**
     * 转换分页结果
     */
    public static <T> PageInfo<T> build(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPage((int)page.getCurrent());//页码
        pageInfo.setPageSize((int)page.getSize());//页面大小
        pageInfo.setRows(page.getRecords());//分页结果
        pageInfo.setTotalRecords((int)page.getTotal());//总记录数
        pageInfo.setTotalPage((int)page.getPages());//总页数
        return pageInfo;
    }

    /**
     * 获取JPA的分页对象
     */
    public static Page getJPAPage(Query query, PageRequest pageRequest, EntityManager em) {
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        //获取分页结果
        return PageableExecutionUtils.getPage(query.getResultList(), pageRequest, () -> {
            //设置countQuerySQL语句
            Query countQuery = em.createNativeQuery("select count(*) from ( " + ((NativeQueryImpl) query).getQueryString() + " ) count_table");
            //设置countQuerySQL参数
            query.getParameters().forEach(parameter -> countQuery.setParameter(parameter.getName(), query.getParameterValue(parameter.getName())));
            //返回一个总数
            return Long.valueOf(countQuery.getResultList().get(0).toString());
        });
    }
}
