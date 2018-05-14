package com.frame.redis.impl;

import com.frame.redis.IStringRedisHelperService;
import com.frame.redis.base.impl.RedisCacheServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>字符缓存通用操作接口实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public class StringRedisHelperServiceImpl extends RedisCacheServiceImpl<String, String> implements IStringRedisHelperService {
	/** 缓存操作实例 **/
	private StringRedisTemplate redisTemplate;
	
	/**
	 * <p>根据缓存键模糊匹配删除</p>
	 */
	public void matchDel(String pattern) {
		super.matchDel(pattern.concat("*"));
	}
	
	/**
	 * <p>获取数据操作模版</p>
	 */
	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}
	
	/**
	 * <p>设置数据操作模版</p>
	 * @param redisTemplate 数据操作模版<br>
	 */
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
