package com.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.service.IUserService;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{

	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public List<User> findAllUser() {
		
		System.out.println("================================================" + userMapper);
		
		return userMapper.findAllUser();
	}

	@Override
	public List<User> findAllUser(RowBounds rowBounds) {
		
		System.out.println("================================================" + userMapper);
		
		return userMapper.findAllUser(rowBounds);
	}

	@Override
	public User addUser(User user) {
		
		return userMapper.addUser(user);
	}

	@Override
	public List<User> queryUsersByPage(int currPage, int pageSize) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currPage", currPage);
		paramMap.put("pageSize", pageSize);
		return userMapper.queryUsersByPage(paramMap);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
	public List<User> queryRolesByPage(String NAME, int start, int limit) {
		
		return userMapper.queryUsers(NAME, new RowBounds(start, limit));
	}
	
}
