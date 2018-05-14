package com.frame.redis;

import java.io.Serializable;
import com.frame.redis.base.IRedisCacheService;

/**
 * <p>字符缓存通用操作接口实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public interface IObjectRedisHelperService extends IRedisCacheService<Serializable, Object> {
}
