package com.demo.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Student implements Serializable{
	private static final long serialVersionUID = -4608305206630669444L;
	
	private String name;
	private String pwd;
	private String[] ids;
	private List<Student> students;
	private MultipartFile imageData;
//	private MultipartFile[] imageDatas;
	
	public Student() {
		super();
	}
	public Student(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public MultipartFile getImageData() {
		return imageData;
	}
	public void setImageData(MultipartFile imageData) {
		this.imageData = imageData;
	}
//	public MultipartFile[] getImageDatas() {
//		return imageDatas;
//	}
//	public void setImageDatas(MultipartFile[] imageDatas) {
//		this.imageDatas = imageDatas;
//	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", pwd=" + pwd + ", ids=" + Arrays.toString(ids) + ", students=" + students
				+ "]";
	}
}
