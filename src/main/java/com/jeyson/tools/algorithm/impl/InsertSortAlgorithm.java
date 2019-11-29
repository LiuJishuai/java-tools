package com.jeyson.tools.algorithm.impl;

import com.jeyson.tools.algorithm.JeysonAlgorithm;

/**
 * @Author: liujishuai
 * @Time: 2019-11-28 16:04
 * @Description:
 */
public class InsertSortAlgorithm implements JeysonAlgorithm {


    @Override
    public void sort(int[] arr) {
        if(null==arr || arr.length==0){
            return ;
        }

        int length = arr.length;
        if(length<2){
            return ;
        }
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            //insertVal准备和前一个数比较

            int index = i - 1;
            while (index >= 0 && insertVal < arr[index]) {
                //将arr[index]向后移动一位
                arr[index + 1] = arr[index];
                index--;
            }
            //将insertVal的值插入适当位置
            arr[index + 1] = insertVal;
        }

    }
}
