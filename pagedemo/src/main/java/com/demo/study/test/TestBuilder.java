package com.demo.study.test;

import com.demo.study.entity.BuilderEntity;
import com.demo.study.entity.BuilderEntity2;

public class TestBuilder {
	public static void main(String[] args) {
		BuilderEntity builderEntity = new BuilderEntity.Builder("Bom", "man").age(20).number(001).build();
		System.out.println(builderEntity);
		builderEntity = new BuilderEntity.Builder("Bom", "man").age(20).number(001).build();
		System.out.println(builderEntity);
		
		// 测试带builder接口实体类
		BuilderEntity2 builderEntity2 = new BuilderEntity2.Builder("man", "jack").age(10).number(002).build();
		
	}
}
