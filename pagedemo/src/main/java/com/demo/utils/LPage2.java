package com.demo.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LPage2<T> {
	
	private int pageNo = 1;//当前页
	private int pageSize = 5;//当前页显示数量
	private int totalNo;//总页数
	private int prevNo = 1;//前一页
	private int nextNo = 1;//下一页
	int totalCount = 1;//总数据量
	private List<T> list;//当前页显示内容
	
	public int getTotalNo() {
		return totalNo;
	}

	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}

	public LPage2(List<T> list,int pageSize,HttpServletRequest req){
		this.pageSize = pageSize;
		System.out.println("现在的也多少：" + this.pageSize);
		this.totalCount = list.size();
		//计算总页数
		totalNo = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
		
		if (req.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
		}
		
		if(Integer.parseInt(req.getParameter("pageNo")) > totalNo) {
			pageNo = totalNo;
		}
		
		if(Integer.parseInt(req.getParameter("pageNo")) < 1) {
			pageNo = 1;
		}

		//计算前页和下页
		prevNo = pageNo == 1 ? 1 : (pageNo - 1);
		nextNo = pageNo == totalNo ? totalNo : (pageNo + 1);
		
		if(pageNo*pageSize > totalCount) {
			this.list = list.subList((pageNo-1)*pageSize, totalCount);
		}else {
			this.list = list.subList((pageNo-1)*pageSize, pageNo*pageSize);
		}
		
	}

	public List<T> getList() {
		return list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPrevNo() {
		return prevNo;
	}

	public void setPrevNo(int prevNo) {
		this.prevNo = prevNo;
	}

	public int getNextNo() {
		return nextNo;
	}

	public void setNextNo(int nextNo) {
		this.nextNo = nextNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
