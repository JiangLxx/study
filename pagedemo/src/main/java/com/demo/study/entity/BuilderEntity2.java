package com.demo.study.entity;

import com.auth0.jwt.internal.org.apache.commons.io.filefilter.AgeFileFilter;
import com.demo.study.infertace.BuilderInterface;

import scala.inline;

/**
 * <p>测试builder模式entity———带builder接口</p>
 * @author jianglan
 *
 */
public class BuilderEntity2 {
	private int age;
	private int number;
	private int height;
	private final String sex;
	private final String name;
	
	private BuilderEntity2(Builder builder) {
		this.age = builder.age;
		this.number = builder.number;
		this.height = builder.height;
		this.sex = builder.sex;
		this.name = builder.name;
	}

	public static class Builder implements BuilderInterface<BuilderEntity2>{
		private int age;
		private int number;
		private int height;
		private final String sex;
		private final String name;
		
		public Builder(String sex, String name) {
			this.sex = sex;
			this.name = name;
		}

		public Builder age(int val) {
			age = val; return this;
		}
		public Builder number(int val) {
			number = val; return this;
		}
		public Builder height(int val) {
			height = val; return this;
		}
		@Override
		public BuilderEntity2 build() {
			return new BuilderEntity2(this);
		}
		
	}

	public int getAge() {
		return age;
	}

	public int getNumber() {
		return number;
	}

	public int getHeight() {
		return height;
	}

	public String getSex() {
		return sex;
	}

	public String getName() {
		return name;
	}
}
