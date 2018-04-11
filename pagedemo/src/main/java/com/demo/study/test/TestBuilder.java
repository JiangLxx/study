package com.demo.study.test;

import com.demo.study.entity.BuilderEntity;

public class TestBuilder {
	public static void main(String[] args) {
		BuilderEntity builderEntity = new BuilderEntity.Builder("Bom", "man").age(20).number(1).build();
		System.out.println(builderEntity.getName());
		System.out.println(builderEntity.getSex());
		System.out.println(builderEntity.getAge());
		System.out.println(builderEntity.getNumber());
	}
}
