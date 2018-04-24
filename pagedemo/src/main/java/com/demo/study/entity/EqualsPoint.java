package com.demo.study.entity;

public class EqualsPoint {
	private final int x;
	private final int y;
	
	public EqualsPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof EqualsPoint)) {
			return false;
		}
		EqualsPoint point = (EqualsPoint) obj;
		return point.x==x && point.y==y;
	}
}
