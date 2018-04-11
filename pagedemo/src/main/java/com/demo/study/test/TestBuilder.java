package com.demo.study.test;

import com.demo.study.entity.BuilderEntity;

public class TestBuilder {
	public static void main(String[] args) {
		BuilderEntity builderEntity = new BuilderEntity.Builder("Bom", "man").age(20).number(001).build();
		System.out.println(builderEntity);
		builderEntity = new BuilderEntity.Builder("Bom", "man").age(20).number(001).build();
		System.out.println(builderEntity);
	}
}
