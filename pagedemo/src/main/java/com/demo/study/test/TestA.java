package com.demo.study.test;

import java.lang.reflect.*;
import com.demo.study.entity.A;

/**
 * 测试反射修改类的私有字段
 * getFields()与getDeclaredFields()区别：
 		getFields()只能访问类中声明为公有的字段,私有的字段它无法访问.
 		getDeclaredFields()能访问类中所有的字段,与  public,private,protect无关 
 *getMethods()与getDeclaredMethods()区别：
		getMethods()只能访问类中声明为公有的方法,私有的方法它无法访问,能访问从其它类继承来的公有方法.
		getDeclaredFields()能访问类中所有的字段,与public,private,protect无关,不能访问从其它类继承来的方法 
 *getConstructors()与getDeclaredConstructors()区别：
		getConstructors()只能访问类中声明为public的构造函数.
		getDeclaredConstructors()能访问类中所有的构造函数,与public,private,protect无关
 * @author jianglan
 *
 */
public class TestA {
	public static void main(String[] args) {
		A a = new A();
		Field[] fields = a.getClass().getDeclaredFields();
		System.out.println("fields长度：" + fields.length);
		AccessibleObject.setAccessible(fields, true);
		try {
			System.out.println(fields[0].toString() + "=" + fields[0].get(a));
			fields[0].setInt(a, 111);
			System.out.println(fields[0].toString() + "=" + fields[0].get(a));
		} catch (IllegalAccessException ex) {  
			ex.printStackTrace();
        } catch (IllegalArgumentException ex) { 
        	ex.printStackTrace();
        }
	}
}
