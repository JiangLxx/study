package com.demo.study.test;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 测试类
 * @author jianglan
 *
 */
public class Test {
	public static void main(String[] args) {
//		测试基本类和包装类计算整数和所用时间差别
//		test1(); test2();
//		测试结果
//		test1:2305843005992468481
//		test1耗费时间：10077
//		test2:2305843005992468481
//		test2耗费时间：695
		
		//测试weakHashMap,自动被GC回收
//		testWeakHashMap();
		
	}

	private static void testWeakHashMap() {
		Map<String,Integer> map = new WeakHashMap<>();
		map.put("s1", 1);
		map.put("s2", 2);
		map.put("s3", 3);
		map.put("s4", 4);
		map.put("s5", 5);
		map.put(null, 9);
		map.put("s6", 6);
        map.put("s7", 7);
        map.put("s8", 8);
        map.put(null, 11);
        for(Map.Entry<String,Integer> entry:map.entrySet())
        {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println(map);
	}

	private static void test1() {
		long time1 = System.currentTimeMillis();
		Long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum +=i;
		}
		System.out.println("test1:" + sum);
		long time2 = System.currentTimeMillis();
		System.out.println("test1耗费时间：" + (time2-time1));
	}
	private static void test2() {
		long time1 = System.currentTimeMillis();
		long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum +=i;
		}
		System.out.println("test2:" + sum);
		long time2 = System.currentTimeMillis();
		System.out.println("test2耗费时间：" + (time2-time1));
	}
}
