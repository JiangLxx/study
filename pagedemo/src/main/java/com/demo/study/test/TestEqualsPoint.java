package com.demo.study.test;

import java.awt.Color;

import com.demo.study.entity.ColorPoint;
import com.demo.study.entity.EqualsPoint;

public class TestEqualsPoint {
	public static void main(String[] args) {
		EqualsPoint point = new EqualsPoint(1, 2);
		ColorPoint color = new ColorPoint(1, 2, Color.RED);
		System.out.println(point.equals(color));
		System.out.println(color.equals(point));
	}
}
