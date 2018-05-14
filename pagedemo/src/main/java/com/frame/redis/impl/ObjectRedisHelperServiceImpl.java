package com.frame.redis.impl;

import java.io.Serializable;
import com.frame.redis.IObjectRedisHelperService;
import com.frame.redis.base.impl.RedisCacheServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>字符缓存通用操作接口实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public class ObjectRedisHelperServiceImpl extends RedisCacheServiceImpl<Serializable, Object> implements IObjectRedisHelperService {
	/** 缓存操作实例 **/
	private RedisTemplate<Serializable, Object> redisTemplate;
	
	/**
	 * <p>获取数据操作模版</p>
	 */
	public RedisTemplate<Serializable, Object> getRedisTemplate() {
		return redisTemplate;
	}
	
	/**
	 * <p>设置数据操作模版</p>
	 * @param redisTemplate 数据操作模版<br>
	 */
	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
