package com.sds.declarative.test;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.declarative.test
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月07日 下午4:42
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

public class Test001 {


    @Test
    public void test001(){
        int[] x = {1,2,3,4,5};
        System.out.println(Arrays.stream(x).count());
        ArrayList list = new ArrayList();
        System.out.println(list.parallelStream().count());
    }


    @Test
    public void test002(){
        IntStream stream = IntStream.builder().add(3).add(2).add(3).build();
//        System.out.println(stream.findAny().getAsInt());

//        System.out.println(stream.average());

        stream.forEach((i)->{
            System.out.println(i);
        });

        List<String> strings = new ArrayList<String>(){{add("1");add("3");}};
        Set<String> x = strings.stream().map(String::toUpperCase).collect(Collectors.toSet());
        x.forEach((item)->{
            System.out.println(item);
        });

        strings.stream().map((Function<String, Integer>) s -> {
            return Integer.parseInt(s);
        }).forEach((item) -> System.out.println(item+100));
    }

    @Test
    public void test004(){
        Stream<List<Map<String,String>>> stream = Stream.of(new ArrayList<Map<String,String>>(){{
            add(new HashMap<String,String>(){{put("username","tanchang");put("sex","M"); }});
            add(new HashMap<String,String>(){{put("username","lisi");put("sex","M"); }});
            add(new HashMap<String,String>(){{put("username","wangwu");put("sex","F"); }});
            add(new HashMap<String,String>(){{put("username","zhaoliu");put("sex","M"); }});
            add(new HashMap<String,String>(){{put("username","tianqi");put("sex","F"); }});
            add(new HashMap<String,String>(){{put("username","kuangba");put("sex","F"); }});
        }});

//        Stream xx = stream.flatMap((x)->{
//            x.stream();
//        });


        /**获取单词，并且去重**/
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
                "hello world welcome");

        //map和flatmap的区别
        list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("---------- ");
        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);

        //实际上返回的类似是不同的
        List<Stream<String>> listResult = list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());
        List<String> listResult2 = list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());

        System.out.println("---------- ");

        //也可以这样
        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("================================================");

        /**相互组合**/
        List<String> list2 = Arrays.asList("hello", "hi", "你好");
        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);
        list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);

        //实际上返回的类似是不同的
        List<Stream<String>> list2Result = list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
        List<String> list2Result2 = list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());



    }


    /**
     * Function 函数式编程 案例
     * compose 组合 组合的优先
     * andThan 先后问题
     */
    @Test
    public void test003(){

        Function<Integer,Integer> times1 = e -> e + 1;
        Function<Integer,Integer> times2 = e -> e * 2;
        Function<Integer,Integer> times3 = e -> e * e;
        Function<Integer,Integer> times4 = e -> e - 100;

//        执行顺序为 time4 -> times2 -> times1 -> times3  ((1000-100)*2+1)^2 = 3243601
        int x = times1.compose(times2).andThen(times3).compose(times4).apply(1000);

    }
//    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList(){{add("张三");
//        add("李四");
//        add("王五");
//        add("张三");}};
//        System.out.println(list.parallelStream().count());
//        list.parallelStream().distinct().filter((predicate)->{
////            System.out.println(predicate);
//            return true;
//        }).forEach((item)->{
//            System.out.println(item);
//        });
//    }
}
