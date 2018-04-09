package com.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.demo.pojo.User;

public interface UserMapper {
	
	public List<User> findAllUser();
	
    public List<User> findAllUser(RowBounds rowBounds);
	
    public User addUser(User user);
    
    @SuppressWarnings("rawtypes")
	public List<User> queryUsersByPage(Map paramMap);
    
    public List<User> queryUsers(String NAME, RowBounds rowBounds);
   
}
