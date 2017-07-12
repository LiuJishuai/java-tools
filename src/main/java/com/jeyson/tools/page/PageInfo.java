package com.jeyson.tools.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 20:14
 * Description:
 */
public class PageInfo implements Serializable {
    /**
     * 页索引
     */
    Integer pageIndex;
    /**
     * 页大小
     */
    Integer pageSize;
    /**
     * 总页数
     */
    Integer pageNum;
    /**
     * 总数据条数
     */
    Integer totalNum;
    /**
     * 列表数据
     */
    List list;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PageInfo() {
        super();
    }

    public PageInfo(Integer pageIndex, Integer pageSize, Integer pageNum, Integer totalNum, List list) {
        super();
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalNum = totalNum;
        this.list = list;
    }
}
