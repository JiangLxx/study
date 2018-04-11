package com.demo.study.entity;

/**
 * <p>测试builder模式entity</p>
 * @author jianglan
 *
 */
public class BuilderEntity {
	private final int age;
	private final int number;
	private final int height;
	private final String sex;
	private final String name;
	
	private BuilderEntity(Builder builder) {
		this.age = builder.age;
		this.number = builder.number;
		this.height = builder.height;
		this.sex = builder.sex;
		this.name = builder.name;
	}
	
	public static class Builder{
		private final String name;
		private final String sex;
		private int age = 0;
		private int number = 0;
		private int height = 0;
		
		public Builder(String name, String sex) {
			this.name = name;
			this.sex = sex;
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
		
		public BuilderEntity build() {
			return new BuilderEntity(this);
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
