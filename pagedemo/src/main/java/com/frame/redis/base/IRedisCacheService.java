package com.frame.redis.base;

import java.util.*;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>字符缓存通用操作接口实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public interface IRedisCacheService<K, V> {
	/**
	 * <p>根据缓存键获取缓存值</p>
	 * @param key 缓存键<br>
	 * @return 缓存值<br>
	 */
	public V get(K key);
	
	/**
	 * <p>根据缓存键获取缓存数组长度</p>
	 * @param key 缓存键<br>
	 * @return 数组长度<br>
	 */
	public long lSize(K key);
	
	/**
	 * <p>根据缓存键获取缓存数组长度</p>
	 * @param key 缓存键<br>
	 * @return 数组长度<br>
	 */
	public long sSize(K key);
	
	/**
	 * <p>根据缓存键模糊查询</p>
	 * @param key 缓存键<br>
	 * @return 
	 */
	public Set<K> keys(K key);
	
	/**
	 * <p>根据缓存键获取集合值</p>
	 * @param key 缓存键<br>
	 * @return 集合值<br>
	 */
	public Set<V> sGet(K key);
	
	/**
	 * <p>删除指定缓存键的缓存</p>
	 * @param keys 缓存键<br>
	 */
	public void del(K... keys);
	
	/**
	 * <p>获取指定缓存的失效时间</p>
	 * @param key 缓存键<br>
	 * @return true:成功 false:失败<br>
	 */
	public long getExpire(K key);
	
	/**
	 * <p>获取指定缓存是否存在</p>
	 * @param key 缓存键<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public boolean hasKey(K key);
	
	/**
	 * <p>根据缓存键模糊匹配删除</p>
	 * @param pattern 模糊键<br>
	 */
	public void matchDel(K pattern);
	
	/**
	 * <p>根据缓存键与索引值获取指定位置的值</p>
	 * @param key 缓存键<br>
	 * @param index 索引值<br>
	 * @return 数据值<br>
	 */
	public V lGet(K key, long index);
	
	/**
	 * <p>根据缓存键获取哈希数组指定键的值</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br>
	 * @return 哈希值<br>
	 */
	public V hGet(K key, Object item);
	
	/**
	 * <p>设置普通缓存信息</p>
	 * @param key 缓存键<br>
	 * @param value 缓存值<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean set(K key, V value);
	
	/**
	 * <p>根据缓存键删除哈希键指定的哈希值</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br>
	 */
	public void hDel(K key, V... item);
	
	/**
	 * <p>根据缓存键递增属性值</p>
	 * @param key 缓存键<br>
	 * @param value 递增值<br>
	 * @return 递增后的值<br>
	 */
	public long incr(K key, long value);
	
	/**
	 * <p>根据缓存递减属性值</p>
	 * @param key 缓存键<br>
	 * @param value 递减值<br>
	 * @return 递减后的值<br>
	 */
	public long decr(K key, long value);
	
	/**
	 * <p>根据缓存键删除数组值指定的数组</p>
	 * @param key 缓存键<br>
	 * @param value 数据值<br>
	 * @return 影响记录数量<br>
	 */
	public long sDel(K key, V... value);
	
	/**
	 * <p>根据缓存键设置数组值</p>
	 * @param key 缓存键<br>
	 * @param value 数组值<br>
	 * @return 影响记录数量<br>
	 */
	public long sSet(K key, V... value);
	
	/**
	 * <p>根据缓存键设置数组值</p>
	 * @param key 缓存键<br>
	 * @param value 数据值<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public boolean lSet(K key, V value);
	
	/**
	 * <p>根据缓存键判断数组中是否包含指定值</p>
	 * @param key 缓存键<br>
	 * @param value 指定值<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public boolean sHasKey(K key, V value);
	
	/**
	 * <p>根据缓存键将指定的数组进行缓存</p>
	 * @param key 缓存键<br>
	 * @param value 数组<br>
	 * @return 影响记录数量<br>
	 */
	public long lSet(K key, List<V> value);
	
	/**
	 * <p>设置指定缓存的失效时间</p>
	 * @param key 索引键<br>
	 * @param time 时间(秒)<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean expire(K key, long time);
	
	/**
	 * <p>根据缓存键获取缓存的哈希数组值</p>
	 * @param key 缓存键<br>
	 * @return 缓存值<br>
	 */
	public Map<Object, Object> hmGet(K key);
	
	/**
	 * <p>根据缓存键判断哈希数组中是否包含指定键</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public boolean hHasKey(K key, Object item);
	
	/**
	 * <p>根据缓存键对指定的数组进行指定时长缓存</p>
	 * @param key 缓存键<br>
	 * @param value 缓存值<br>
	 * @param time 缓存时长<br>
	 * @return 影响记录数量<br>
	 */
	public long lSet(K key, V value, long time);
	
	/**
	 * <p>根据参数值缓存指定对象</p>
	 * @param key 缓存键<br>
	 * @param value 缓存值<br>
	 * @param time 缓存时间<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean set(K key, V value, long time);
	
	/**
	 * <p>获取数据操作模版</p>
	 * @return 数据操作模版<br>
	 */
	public RedisTemplate<K, V> getRedisTemplate();
	
	/**
	 * <p>根据缓存键对指定数组进行指定时长缓存</p>
	 * @param key 缓存键<br>
	 * @param time 缓存时长<br>
	 * @param value 数组值<br>
	 * @return 影响记录数量<br>
	 */
	public long sSet(K key, long time, V... value);
	
	/**
	 * <p>根据缓存键对指定的哈希数组进行缓存</p>
	 * @param key 缓存键<br>
	 * @param map 哈希数组<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean hSet(K key, Map<Object, V> map);
	
	/**
	 * <p>根据缓存键对指定的哈希数组进行缓存</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br>
	 * @param value 哈希值<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean hSet(K key, Object item, V value);
	
	/**
	 * <p>根据缓存键从缓存数组中获取指定位置的数据集:0至负1代表所有值</p>
	 * @param key 缓存键<br>
	 * @param start 开始位置<br>
	 * @param end 结束位置<br>
	 * @return 数据集<br>
	 */
	public List<V> lGet(K key, long start, long end);
	
	/**
	 * <p>根据存储键删除从指定索引值开始的数据</p>
	 * @param key 存储键<br>
	 * @param count 索引值<br>
	 * @param value 数据值<br>
	 * @return 影响记录数量<br>
	 */
	public long lDel(K key, long count, Object value);
	
	/**
	 * <p>根据缓存键对指定的数组进行指定时长缓存</p>
	 * @param key 缓存键<br>
	 * @param value 缓存值<br>
	 * @param time 缓存时长<br>
	 * @return 影响记录数量<br>
	 */
	public long lSet(K key, List<V> value, long time);
	
	/**
	 * <p>根据缓存键及索引值更新缓存中的指定值</p>
	 * @param key 缓存键<br>
	 * @param index 索引值<br>
	 * @param value 数据值<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean lUpdate(K key, long index, V value);
	
	/**
	 * <p>根据缓存键对哈希数组中指定哈希键值进行递增</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br>
	 * @param value 递增值<br>
	 * @return 递增后的值<br>
	 */
	public double hIncr(K key, Object item, double value);
	
	/**
	 * <p>根据缓存键对哈希数组中指定哈希键值进行递减</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br> 
	 * @param value 递减值<br>
	 * @return 递减后的值<br>
	 */
	public double hDecr(K key, Object item, double value);
	
	/**
	 * <p>根据缓存键对指定的哈希数值进行指定时长缓存</p>
	 * @param key 缓存键<br>
	 * @param item 哈希键<br>
	 * @param value 哈希值<br>
	 * @param time 缓存时长<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean hSet(K key, Object item, V value, long time);
	
	/**
	 * <p>根据缓存键对指定的哈希数组进行指定时长缓存</p>
	 * @param key 缓存键<br>
	 * @param map 哈希数组<br>
	 * @param time 缓存时长<br>
	 * @return true:成功 false:失败<br>
	 */
	public boolean hSet(K key, Map<Object, Object> map, long time);
}

