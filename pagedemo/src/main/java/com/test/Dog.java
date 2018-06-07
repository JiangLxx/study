package com.test;

public class Dog {
	public int age;
    public String name;
    public Dog(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Dog [age=" + age + ", name=" + name + "]";
    }
}
