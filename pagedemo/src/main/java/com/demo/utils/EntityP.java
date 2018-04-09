package com.demo.utils;

import java.io.Serializable;
import java.util.List;

public class EntityP implements Serializable {
	private static final long serialVersionUID = 3451513870132491607L;
	private String id;
	private String method;
	private String methodNm;
	private String wb_title;
	private String according;
	private String sign_name;
	private String call_phone;
	private String accordingNm;
	private String sign_nameNm;
	private String call_phoneNm;
	private String company_name;
	private List<String> titles;
	private String company_nameNm;
	private List<Entity> entities;
	private String description;
	private String option1;
	private String option2;
	private String subTitle;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getWb_title() {
		return wb_title;
	}
	public void setWb_title(String wb_title) {
		this.wb_title = wb_title;
	}
	public String getAccording() {
		return according;
	}
	public void setAccording(String according) {
		this.according = according;
	}
	public String getSign_name() {
		return sign_name;
	}
	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}
	public String getCall_phone() {
		return call_phone;
	}
	public void setCall_phone(String call_phone) {
		this.call_phone = call_phone;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	public String getMethodNm() {
		return methodNm;
	}
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}
	public String getAccordingNm() {
		return accordingNm;
	}
	public void setAccordingNm(String accordingNm) {
		this.accordingNm = accordingNm;
	}
	public String getSign_nameNm() {
		return sign_nameNm;
	}
	public void setSign_nameNm(String sign_nameNm) {
		this.sign_nameNm = sign_nameNm;
	}
	public String getCall_phoneNm() {
		return call_phoneNm;
	}
	public void setCall_phoneNm(String call_phoneNm) {
		this.call_phoneNm = call_phoneNm;
	}
	public String getCompany_nameNm() {
		return company_nameNm;
	}
	public void setCompany_nameNm(String company_nameNm) {
		this.company_nameNm = company_nameNm;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
}
