package com.demo.study.entity;

/**
 * 枚举类型的单例模式（不太懂）
 * @author jianglan
 *
 */
public enum EnumSingleton {
	A,B;
	public void leaveTheBuilding(String method) {
        System.out.println(method);
    }
	public static void main(String[] args) {
		EnumSingleton enumSingleton = EnumSingleton.A;
		EnumSingleton enumSingleton2 = EnumSingleton.B;
		enumSingleton.leaveTheBuilding("a");
		enumSingleton2.leaveTheBuilding("b");
	}
}
