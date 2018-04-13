package com.demo.study.entity;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
	public Object[] elements;
	public int size = 0;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public Object pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		//**********************************
		//避免内存泄露，在移除栈时将此对象引用置空，方便GC回收
		Object result = elements[--size];
		elements[--size] = null;
		//**********************************
		return result;
	}
	
	private void ensureCapacity() {
		if(elements.length == size) {
			elements = Arrays.copyOf(elements, 2*size + 1);
		}
	}
}
