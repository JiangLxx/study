package com.frame.redis.base.impl;

import java.util.Map;
import java.util.Set;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.idiot.utils.CommHelper;
import java.util.concurrent.TimeUnit;
import com.frame.redis.base.IRedisCacheService;
import org.springframework.util.CollectionUtils;

/**
 * <p>REDIS数据库通用操作接口实现类</p>
 * @author FLY @date 2017-12-19<br>
 * @version 1.0<br>
 */
@SuppressWarnings("unchecked")
public abstract class RedisCacheServiceImpl<K, V> implements IRedisCacheService<K, V> {
	/** 日志实例 **/
	private Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	
	/**
	 * <p>根据缓存键获取缓存值</p>
	 */
	public V get(K key) {
		V rtnO = null;
		if (CommHelper.isNotNull(key)) {
			rtnO = getRedisTemplate().opsForValue().get(key);
		}
		return rtnO;
	}
	
	/**
	 * <p>根据缓存键获取缓存数组长度</p>
	 */
	public long lSize(K key) {
		long rtnL = 0;
		try {
			if (CommHelper.isNotNull(key)) {
				rtnL = getRedisTemplate().opsForList().size(key);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键获取缓存数组长度</p>
	 */
	public long sSize(K key) {
		long rtnL = 0;
		try {
			if (CommHelper.isNotNull(key)) {
				rtnL = getRedisTemplate().opsForSet().size(key);
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键模糊查询键集</p>
	 */
	public Set<K> keys(K key) {
		return getRedisTemplate().keys((K)key.toString().concat("*"));
	}
	
	/**
	 * <p>根据缓存键获取集合值</p>
	 */
	public Set<V> sGet(K key) {
		Set<V> rtnS = null;
		try {
			if (CommHelper.isNotNull(key)) {
				rtnS = getRedisTemplate().opsForSet().members(key);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>删除指定缓存键的缓存</p>
	 */
	public void del(K... keys) {
		// 合法性判断
		if (CommHelper.isNotEmptyArray(keys)) {
			if (keys.length == 1) {
				getRedisTemplate().delete(keys[0]);
			} else {
				getRedisTemplate().delete(CollectionUtils.arrayToList(keys));
			}
		}
	}
	
	/**
	 * <p>获取指定缓存的失效时间</p>
	 */
	public long getExpire(K key) {
		return getRedisTemplate().getExpire(key, TimeUnit.SECONDS);
	}
	
	/**
	 * <p>获取指定缓存是否存在</p>
	 */
	public boolean hasKey(K key) {
		boolean rtnB = false;
		try {
			rtnB = getRedisTemplate().hasKey(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键模糊匹配删除</p>
	 */
	public void matchDel(K pattern) {
		Set<K> keys = getRedisTemplate().keys(pattern);
		if (CommHelper.isNotEmptySet(keys)) {
			getRedisTemplate().delete(keys);
		}
	}
	
	/**
	 * <p>根据缓存键与索引值获取指定位置的值</p>
	 */
	public V lGet(K key, long index) {
		V rtnO = null;
		try {
			rtnO = getRedisTemplate().opsForList().index(key, index);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>根据缓存键获取哈希数组指定键的值</p>
	 */
	public V hGet(K key, Object item) {
		V rtnO = null;
		try {
			rtnO = (V) getRedisTemplate().opsForHash().get(key, item);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>设置普通缓存信息</p>
	 */
	public boolean set(K key, V value) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForValue().set(key, value); rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键删除哈希键指定的哈希值</p>
	 */
	public void hDel(K key, V... item) {
		getRedisTemplate().opsForHash().delete(key, item);
	}
	
	/**
	 * <p>根据缓存键递增属性值</p>
	 */
	public long incr(K key, long value) {
		long rtnL = 0;
		try {
			if (value <= 0) {
				throw new RuntimeException("递增因子必须大于0.");
			}
			rtnL = getRedisTemplate().opsForValue().increment(key, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存递减属性值</p>
	 */
	public long decr(K key, long value) {
		long rtnL = 0;
		try {
			if (value <= 0) {
				throw new RuntimeException("递增因子必须大于0.");
			}
			rtnL = getRedisTemplate().opsForValue().increment(key, -value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>设置指定缓存的失效时间</p>
	 */
	public boolean expire(K key, long time) {
		boolean rtnB = false;
		try {
			rtnB = getRedisTemplate().expire(key, time, TimeUnit.SECONDS);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键获取缓存的哈希数组值</p>
	 */
	public Map<Object, Object> hmGet(K key) {
		Map<Object, Object> rtnMap = null;
		try {
			rtnMap = getRedisTemplate().opsForHash().entries(key);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnMap;
	}
	
	/**
	 * <p>根据缓存键删除数组值指定的数组</p>
	 */
	public long sDel(K key, V... value) {
		long rtnL = 0l;
		try {
			rtnL = getRedisTemplate().opsForSet().remove(key, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键设置数组值</p>
	 */
	public long sSet(K key, V... value) {
		long rtnL = 0;
		try {
			rtnL = getRedisTemplate().opsForSet().add(key, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键设置数组值</p>
	 */
	public boolean lSet(K key, V value) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForList().rightPush(key, value); rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键判断数组中是否包含指定值</p>
	 */
	public boolean sHasKey(K key, V value) {
		boolean rtnB = false;
		try {
			rtnB = getRedisTemplate().opsForSet().isMember(key, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键将指定的数组进行缓存</p>
	 */
	public long lSet(K key, List<V> value) {
		long rtnL = 0;
		try {
			rtnL = getRedisTemplate().opsForList().rightPushAll(key, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键判断哈希数组中是否包含指定键</p>
	 */
	public boolean hHasKey(K key, Object item) {
		boolean rtnB = false;
		try {
			rtnB = getRedisTemplate().opsForHash().hasKey(key, item);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键对指定的数组进行指定时长缓存</p>
	 */
	public long lSet(K key, V value, long time) {
		long rtnL = 0;
		try {
			rtnL = getRedisTemplate().opsForList().rightPush(key, value);
			if (time > 0) expire(key, time);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据存储键删除从指定索引值开始的数据</p>
	 */
	public long lDel(K key, long index, Object value) {
		long rtnL = 0;
		try {
			rtnL = getRedisTemplate().opsForList().remove(key, index, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据参数值缓存指定对象</p>
	 */
	public boolean set(K key, V value, long time) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForValue().set(key, value, time); rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键对指定数组进行指定时长缓存</p>
	 */
	public long sSet(K key, long time, V... value) {
		long rtnL = 0;
		try {
			rtnL = getRedisTemplate().opsForSet().add(key, value);
			if (time > 0) expire(key, time);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键对指定的哈希数组进行缓存</p>
	 */
	public boolean hSet(K key, Map<Object, V> map) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForHash().putAll(key, map); rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键对指定的哈希数组进行缓存</p>
	 */
	public boolean hSet(K key, Object item, V value) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForHash().put(key, item, value); rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键从缓存数组中获取指定位置的数据集</p>
	 */
	public List<V> lGet(K key, long start, long end) {
		List<V> rtnList = null;
		try {
			rtnList = getRedisTemplate().opsForList().range(key, start, end);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnList;
	}
	
	/**
	 * <p>根据缓存键对指定的数组进行指定时长缓存</p>
	 */
	public long lSet(K key, List<V> value, long time) {
		long rtnL = 0;
		try {
			rtnL = getRedisTemplate().opsForList().rightPushAll(key, value);
			if (time > 0) { expire(key, time); }
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnL;
	}
	
	/**
	 * <p>根据缓存键及索引值更新缓存中的指定值</p>
	 */
	public boolean lUpdate(K key, long index, V value) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForList().set(key, index, value); rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键对哈希数组中指定哈希键值进行递增</p>
	 */
	public double hIncr(K key, Object item, double value) {
		double rtnD = 0.0d;
		try {
			rtnD = getRedisTemplate().opsForHash().increment(key, item, value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnD;
	}
	
	/**
	 * <p>根据缓存键对哈希数组中指定哈希键值进行递减</p>
	 */
	public double hDecr(K key, Object item, double value) {
		double rtnD = 0.0d;
		try {
			rtnD = getRedisTemplate().opsForHash().increment(key, item, -value);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnD;
	}
	
	/**
	 * <p>根据缓存键对指定的哈希数值进行指定时长缓存</p>
	 */
	public boolean hSet(K key, Object item, V value, long time) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForHash().put(key, item, value);
			if (time > 0) { expire(key, time); } rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>根据缓存键对指定的哈希数组进行指定时长缓存</p>
	 */
	public boolean hSet(K key, Map<Object, Object> map, long time) {
		boolean rtnB = false;
		try {
			getRedisTemplate().opsForHash().putAll(key, map);
			if (time > 0) { expire(key, time); } rtnB = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnB;
	}
}
