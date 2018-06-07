package com.test;

import java.io.Serializable;

import org.junit.internal.runners.TestMethod;

public class TestUser implements Serializable{
	private static final long serialVersionUID = 3308249053631649899L;

	private String id;
	
	private String loginname;
	
	private String password;
	
	private String name;
	
	private String gender;
	
	private String telephone;
	
	private String modile;
	
	private String email;
	
	public TestUser() {
		super();
	}

	public TestUser(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}
	
	public TestUser(String name, String gender, String telephone) {
		super();
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
	}

	
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getModile() {
		return modile;
	}

	public void setModile(String modile) {
		this.modile = modile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	private void TestMethod(String abc) {
		System.out.println("测试反射调用私有方法 :" + abc);
	}

	@Override
	public String toString() {
		return "TestUser [id=" + id + ", loginname=" + loginname + ", password=" + password + ", name=" + name
				+ ", gender=" + gender + ", telephone=" + telephone + ", modile=" + modile + ", email=" + email + "]";
	}
	
}
