package com.jeyson.tools.page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 20:17
 * Description: 分页工具类
 */
public class PageUtils {
    public static PageInfo getPageInfo(Integer pageIndex, Integer pageSize, List list) {
        PageInfo pageInfo = new PageInfo();
        if (list == null || list.size() == 0) {
            pageInfo.setPageIndex(pageIndex);
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(0);
            pageInfo.setTotalNum(0);
            pageInfo.setList(null);
        }
        //总数据条数就是列表大小
        Integer totalNum = list.size();
        //总页数
        int pageNum = (totalNum + pageSize - 1) / pageSize;
        //开始索引
        int startIndex = (pageIndex - 1) * pageSize;
        //截止索引。考虑到是最后一页，页内数据量的情况
        int endIndex = pageNum == pageIndex ? startIndex + (totalNum % pageSize == 0 ?
                pageSize : totalNum % pageSize) : startIndex + pageSize;
        //一个新的列表来存放分页后的数据
        List newList = new ArrayList();
        for (int i = startIndex; i < endIndex; i++) {
            newList.add(list.get(i));
        }
        pageInfo.setPageIndex(pageIndex);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        pageInfo.setTotalNum(totalNum);
        pageInfo.setList(newList);
        return pageInfo;

    }
}
