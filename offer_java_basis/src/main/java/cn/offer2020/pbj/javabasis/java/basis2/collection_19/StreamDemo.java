package cn.offer2020.pbj.javabasis.java.basis2.collection_19;//package cn.pbj2019.demo.javabasis.basis2.collection_19;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//public class StreamDemo {
//
//	public static void main(String[] args) {
//		List<String> all = new ArrayList<String>();
//		all.add("A");
//		all.add("B");
//		all.add("C");
//		//利用foreach输出
//		all.forEach(System.out::println);   //foreach只能输出，不能进行数据操作，一般不用
//		//取得Stream对象
//		Stream<String> stream = all.stream();
//		System.out.println(stream.count());
//		//取消重复数据
//		List<String> all2 = new ArrayList<String>();
//		all2.add("A");
//		all2.add("C");
//		all2.add("C");
//		all2.add("B");
//		//去除所有的重复数据形成的集合数据
//		Stream<String> stream2 = all2.stream();
//		List<String> newall = stream2.distinct().collect(Collectors.toList());
//		newall.forEach(System.out::print);
//		//数据过滤
//		//增加数据过滤操作，使用断言型函数式接口，使用String类中的contains()方法
//		List<String> newall2  = stream.distinct().filter((x) -> x.contains("B")).collect(Collectors.toList());
//		newall2.forEach(System.out::println);
//		//数据过滤后来处理
//		List<String> newall3  = stream.distinct().map((x) -> x.toLowerCase()).filter((x) ->x.contains("B")).collect(Collectors.toList());
//		newall3.forEach(System.out::println);
//		//集合数据分页操作
//		List<String> newall4 = stream.distinct().map((x) -> x.toLowerCase()).skip(2).limit(2).collect(Collectors.toList());
//		newall4.forEach(System.out::println);
//		//数据的匹配查询
//		if(stream.allMatch((x) ->x.contains("C"))){
//			System.out.println("数据存在");
//		}
//		//设置多个条件
//		//实现一个MapReduce操作 map处理数据，reduce分析数据
//	}
//
//}
