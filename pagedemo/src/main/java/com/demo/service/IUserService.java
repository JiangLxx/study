package com.demo.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.demo.pojo.User;

public interface IUserService {
	
	public List<User> findAllUser();
    
	public List<User> findAllUser(RowBounds rowBounds);
	
	public User addUser(User user) ;
	
	public List<User> queryUsersByPage(int currPage,int pageSize);
	
	public List<User> queryRolesByPage(String NAME, int start, int limit);
	
}
