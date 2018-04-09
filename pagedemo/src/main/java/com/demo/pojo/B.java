package com.demo.pojo;

import java.io.Serializable;

public class B implements Serializable {
	
	private static final long serialVersionUID = 4356869080401362371L;
	private String name;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
