package com.iluwatar.factorykit.app;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 该类用于测试 lambda表达式
 * 参考 网站: http://www.cnblogs.com/anakin/p/7742779.html
 *
 * @Author liudi
 * @Date Create in 16:40 2018/11/6
 */
public class LambdaTest {

    // 测试匿名内部类 用lambda实现
    @Test
    public void test1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test1");
            }
        }).start();

        // 匿名内部类(匿名内部类 只被调用一次 )
        new Thread(() -> System.out.println("testLambda")).start();

    }

    // JButton
    @Test
    public void test2(){
        JButton show = new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test2");
            }
        });

        show.addActionListener((e) -> {
            System.out.println("testLambda");
        });
    }

    // lambda 输出 及 使用 lambda 引用输出 (不更改参数的情况向可以使用,如果更改参数 需写完整的lambda方程)
    @Test
    public void test3(){
        List<String> features = Arrays.asList("la1","拉","la3","la4");
        for (String feature : features) {
            System.out.println(feature);
        }

       features.forEach(n -> System.out.println(n));
       features.forEach(System.out::println);

    }

    // FIXME 有的字符串方法不能使用?
    @Test
    public void test4(){
        List<String> languages = Arrays.asList("Java","C++","Python");
        /*  startwith 不能正常使用
        System.out.println("语言开始为 J:");
        filter(languages,(str) -> str.startWith("J"));
        */
        System.out.println("Print all languages :");
        filter(languages,(str) -> true);

        System.out.println("Print no languages :");
        filter(languages,(str) -> false);

        /*// 不能正常使用 lenght()方法
        filter(languages, (str) -> str.length() >4);*/

    }

   /* public static void filter(List<String> names,Predicate condition){
        for (String name : names) {
            if (condition.test(name)){
                System.out.println(name + "");
            }
        }
    }*/
    public static void filter(List<String> names,Predicate condition){
        names.stream().filter((name) -> (condition.test(name)))
                .forEach((name) -> System.out.println(name + ""));
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n)-> n.length() ==4;
        names.stream().filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.println("使用and方法合并"));
    }

    // map() 类型转换
    @Test
    public void test6(){
        List<Integer> costBeforeTax = Arrays.asList(100,200,300,400,500);
        for (Integer cost : costBeforeTax) {
            double price = cost + 0.12*cost;
            System.out.println(price);
        }
        // map 做类型转换
        costBeforeTax.stream().map((cost) -> cost + 0.12*cost)
                .forEach(System.out::println);
    }

    // reduce() 相加
    @Test
    public void test6_2(){
        List<Integer> costBeforeTax = Arrays.asList(100,200,300,400,500);
        double total = 0;
        long start1 = System.currentTimeMillis();
        for (Integer cost : costBeforeTax) {
            double price = cost + 0.12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
        System.out.println(" 耗时1为 :" + (System.currentTimeMillis()-start1));
        long start2 = System.currentTimeMillis();
        System.out.println("Total : " +
                costBeforeTax.stream().map((cost) -> cost + 0.12*cost)
                .reduce((sum,cost) -> sum +cost).get()
        );
        System.out.println(" 耗时2为 :" + (System.currentTimeMillis()-start2));
    }

    /*@Test
    public void test7(){
        List<String> filtered = strList.stream().fiter(x -> x.length)
    }*/

    // map() 将小写转为大写
    @Test
    public void test8(){
        List<String> G7 = Arrays.asList("USA","Japan","FRANCE");
        String G7Countries = G7.stream().map(x -> x.toUpperCase())
                .collect(Collectors.joining(","));
        System.out.println(G7Countries);
    }

    @Test
    public void test9(){
        List<Integer> numbers = Arrays.asList(9,10,3,4,7,3,4);
        List<Integer> distinct = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        numbers.forEach(System.out::println);
        System.out.println("-----");
        distinct.forEach((n) -> System.out.println(n + ""));

        // %s 相当于占位符
//        System.out.println("Original List :%s, Square Without duplicates : %s %n",numbers,distinct);
    }

    // mapToInt() ?? 使用工具类 求最大 最小 和 平均值
    @Test
    public void test10(){
        List<Integer> primes = Arrays.asList(2,3,5,7,11,13,17,19,23,29);
        IntSummaryStatistics stats =
                primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("最大值 :" + stats.getMax());
        System.out.println("最小值 :" + stats.getMin());
        System.out.println("总和 :" + stats.getSum());
        System.out.println("平均值 :" + stats.getAverage());
    }

}
