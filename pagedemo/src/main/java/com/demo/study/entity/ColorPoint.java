package com.demo.study.entity;

import java.awt.Color;

public class ColorPoint extends EqualsPoint{
	private Color color;
	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ColorPoint)) {
			return false;
		}
		return super.equals(obj) && ((ColorPoint) obj).color == color;
	}
}