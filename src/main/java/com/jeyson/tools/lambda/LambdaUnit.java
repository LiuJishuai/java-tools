package com.jeyson.tools.lambda;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: liujishuai
 * @Time:
 * @Description:
 */
public class LambdaUnit {

    @Test
    public void testForeach() {
        List list = Arrays.asList("hello", "world", "lambda");
        list.forEach(m -> System.out.println(m));
        list.forEach(System.out::println);
    }

    @Test
    public void testFilter() {
        List<String> list = Arrays.asList("mysql", "java", "c", "python");
        List subList = list.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.println("list=" + JSON.toJSONString(list));
        System.out.println("subList=" + JSON.toJSONString(subList));
    }

    @Test
    public void testOptElement() {
        List<String> list = Arrays.asList("mYsQl", "jAvA", "c", "pYthon");
        List subList = list.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
        String subString = list.stream().map(x -> x.toLowerCase()).collect(Collectors.joining(","));
        System.out.println("list= " + JSON.toJSONString(list));
        System.out.println("subList= " + JSON.toJSONString(subList));
        System.out.println("subString= " + subString);
    }

    @Test
    public void testDistant() {
        List<Integer> list = Arrays.asList(1, 45, 2, 45);
        List subList = list.stream().map(x -> x * 2).distinct().collect(Collectors.toList());
        System.out.println("list= " + JSON.toJSONString(list));
        System.out.println("subList= " + JSON.toJSONString(subList));
    }

    @Test
    public void testMath() {
        List<Integer> list = Arrays.asList(2, 4, 6, 8);
        IntSummaryStatistics statistics = list.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("list= " + JSON.toJSONString(list));
        System.out.println("max= " + statistics.getMax() +
                " min=" + statistics.getMin() +
                " sum=" + statistics.getSum() +
                " avg=" + statistics.getAverage() +
                " count=" + statistics.getCount()
        );

    }

    @Test
    public void  testUpdateDTO(){
//        HelloDTO d1=new HelloDTO(1L,"tx","");
//        HelloDTO d2=new HelloDTO(2L,"wy","");
//        List<HelloDTO> list=new ArrayList<>();
//        list.add(d1);list.add(d2);
//        list.forEach(x->{
//            x.setDesc(x.getName()+":TT");
//        });
//
//        list.forEach(System.out::println);
    }

}
