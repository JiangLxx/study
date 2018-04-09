package com.demo.pojo;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String loginname;
	
	private String password;
	
	private String name;
	
	private String gender;
	
	private String telephone;
	
	private String modile;
	
	private String email;
	
	private String status;
	
	private String description;

	private String ctrlUnitId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCtrlUnitId() {
		return ctrlUnitId;
	}

	public void setCtrlUnitId(String ctrlUnitId) {
		this.ctrlUnitId = ctrlUnitId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", telephone=" + telephone + ", modile=" + modile + ", email=" + email + ", status=" + status
				+ ", description=" + description + ", ctrlUnitId=" + ctrlUnitId + "]";
	}

	
}
