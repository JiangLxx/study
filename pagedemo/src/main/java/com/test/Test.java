package com.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;

import com.demo.utils.wx.constant.Constants;

public class Test {
	public static void main(String[] args) {
//		TestUser target = new TestUser();
//		Class<?> clazz = target.getClass();
//		
//		Field f = clazz.getDeclaredField("id");
//		f.setAccessible(true);
//		f.set(target, "111");
//		System.out.println(target);
//		
//		Method method = clazz.getDeclaredMethod("TestMethod", String.class);
//		method.setAccessible(true);
//		System.out.println(method.invoke(target, "aoiahg"));
		
//		
//		// 测试冒泡排序
//		BubbleSorter sorter = new BubbleSorter();
//		
//		List<Dog> list = new ArrayList<>(); 
//		list.add(new Dog(5, "DogA"));
//	    list.add(new Dog(777, "DogB"));
//	    list.add(new Dog(7, "DogC"));
//		
//		Collections.sort(list, new Comparator<Dog>() {
//			
//			@Override
//			public int compare(Dog o1, Dog o2) {
//				return o1.age - o2.age;
//			}
//		});
//		
//		System.out.println(list);
		
//		String s1 = "Pro";
//		String s2 = new String("Pro");
//		String s3 = "Pro" + "ing";
//		s1+="ing";
//		System.out.println(s1==s2);
//		System.out.println(s1==s3);
//		System.out.println(s1 == s1.intern());
		
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
	}
	
}
