package com.jeyson.tools.lambda;

import org.junit.Test;
import org.w3c.dom.ls.LSInput;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: liujishuai
 * @Time: 2019-11-20 21:32
 * @Description:
 */
public class StreamApiTest {

    List<HelloDTO> list = Arrays.asList(
            new HelloDTO(1L,1,"小王","学习能力强"),
            new HelloDTO(2L,2,"小李","工作能力强"),
            new HelloDTO(3L,1,"小赵","业务能力强")
            );

    @Test
    public void test1() {



       List<HelloDTO> subList = list.stream().filter(x-> {
           return x.getStatus() > 0;

       }).collect(Collectors.toList());

        list.stream().map(x->x.getId()).collect(Collectors.toList());
    }


    @Test
    public void testFilter() {


        list.stream().filter(x->x.getStatus()>0).forEach(System.out::println);
    }
    @Test
    public void testCreate() {

        /**
         * Collection
         */
        Stream stream = list.stream().sorted();
        Arrays.stream(new int[]{1,2,43});
        Stream.of("中国","美国","意大利");

        /**
         * 无限流
         */
        Stream.iterate(0,x->x+2).forEach(System.out::println);
        Stream.generate(Math::random).forEach(System.out::println);

    }

    @Test
    public void testMap() {
        List<String> sk = Arrays.asList("abc,hj","def,ga","jkl,fa");
        sk.stream().map(String::toUpperCase).forEach(System.out::println);
        sk.stream().flatMap(StreamApiTest::splite).forEach(System.out::println);
        long count=sk.stream().count();


    }


    @Test
    public void testSort() {
        List<String> sk = Arrays.asList("abc,hj","def,ga","akl,fa");
        sk.stream().sorted().forEach(System.out::println);
        list.stream().sorted((x,y)-> Integer.compare(x.getStatus(),y.getStatus())).forEach(System.out::println);

    }

    @Test
    public void test12() {
        List<String> sk = Arrays.asList("abc,hj","def,ga","akl,fa");
        sk.stream()
                .filter(x->x.length()>2)
                .map(x->{
            System.out.println(x);
            return x+"-jk";
        });


    }
    @Test
    public void testReduce() {
        List<Integer> sk = Arrays.asList(1,2,3,4,5,6);
        Integer sum=  sk.stream().reduce(0,(x,y)->x+y);
        System.out.println(sum);

        Optional<Integer> sumOp=  sk.stream().reduce(Integer::sum);
        System.out.println(sumOp.get());

    }


    @Test
    public void testCollect() {
        List<Integer> sk = Arrays.asList(1,2,3,4,5,6);

        List<Integer> sList= sk.stream().collect(Collectors.toList());
        Set<Integer> sSet= sk.stream().collect(Collectors.toSet());

        Collection<Integer> seCol= sk.stream().collect(Collectors.toCollection(ArrayList::new));


        Long count= sk.stream().collect(Collectors.counting());
        System.out.println(count);
        Integer sum = sk.stream().collect(Collectors.summingInt(x->x));
        System.out.println(sum);

        Double avg = sk.stream().collect(Collectors.averagingDouble(x->x));
        System.out.println(avg);


        String nameStr= list.stream().map(x->x.getName()).collect(Collectors.joining(","));
        System.out.println(nameStr);

        int statusSum = list.stream().collect(Collectors.reducing(0,HelloDTO::getStatus,Integer::sum));
        System.out.println(statusSum);

        /**
         * map
         */
        Map<Long,HelloDTO> map = list.stream().collect(Collectors.toMap(HelloDTO::getId,x->x));
        System.out.println(map);

        Map<Long,List<HelloDTO>> map2 = list.stream().collect(Collectors.groupingBy(HelloDTO::getId));
        System.out.println(map2);

        Map<Integer,List<HelloDTO>> mapList = list.stream().collect(Collectors.groupingBy(HelloDTO::getStatus));
        System.out.println(mapList);



    }

    @Test
    public void testInterface() {
        Function<HelloDTO,Integer> function = x->x.getStatus();


    }


    private static  Stream<String> splite(String str){
        String[] sp = str.split(",");
        return Arrays.stream(sp);
    }


}
