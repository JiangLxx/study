package com.demo.study.entity;

/**
 * 添加throw new AssertionError()可以成功避免在UtilityClass中实例化UtilityClass类
 * @author jianglan
 *
 */
public class PrivateConstructorEntity {
	public static void main(String[] args) {
		UtilityClass one = UtilityClass .getInstance();
	}
}

class UtilityClass {
    // Suppress default constructor for noninstantiability
    private UtilityClass() {
        throw new AssertionError();
    }
 
    public static UtilityClass getInstance()
    {
        return new UtilityClass();
    }
}

//class UtilityClassSon extends UtilityClass {}