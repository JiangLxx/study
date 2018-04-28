package com.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.pojo.A;
import com.demo.pojo.B;
import com.demo.pojo.Student;

@Controller
public class test {
	
	@ResponseBody @RequestMapping("/testWx")
	public void testWx() {
		System.out.println("服务接通！！！！");
	 } 
	
	@ResponseBody @RequestMapping("/s")
	public void saveUsers(@RequestBody Student student) {
		System.out.println(student);
		 List<Student> students = student.getStudents();
		 for (Student s : students) {
			 System.out.println(s);
		 }
	 } 
	 
	@ResponseBody @RequestMapping("/ss")
	public void testIds(Student student) {
		System.out.println(student);
		System.out.println("ids类型：" + student.getIds().getClass().isArray());
		System.out.println(student.getIds()[0]);
		System.out.println(student.getIds()[1]);
	}
	
	@RequestMapping(value="/sss", method = RequestMethod.POST)
	public void testIds2(@RequestParam(value = "ids[]") String[] ids) {
		System.out.println("ids类型：" + ids.getClass().isArray());
		for(int i=0;i<ids.length;i++){
            System.out.println("777777 : " + ids[i]);
        }
	}
	
	@RequestMapping(value="/a")
	public void saveOrUpdateUser(A a){
	    List<B> userlist = a.getBs();
	    for (B b : userlist) {
			System.out.println("bbb : " + b);
		}
	}
	
	@RequestMapping(value="/upload")
	public void testUpload(@RequestParam("file") MultipartFile file) {
		System.out.println("contentType : " + file.getContentType());
		System.out.println("size : " + file.getSize());
		System.out.println("name : " + file.getName());
		System.out.println("originalFilename : " + file.getOriginalFilename());
		System.out.println("--------------------------------------");
	}
}
