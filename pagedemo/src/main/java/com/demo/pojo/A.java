package com.demo.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class A implements Serializable {

	private static final long serialVersionUID = 5015461225699851581L;
	private ArrayList<B> bs;
	public ArrayList<B> getBs() {
		return bs;
	}
	public void setBs(ArrayList<B> bs) {
		this.bs = bs;
	}

}
