package com.demo.quartz.base;


import org.apache.log4j.Logger;
import com.idiot.utils.CommHelper;
import com.idiot.spg.ContextHelper;
import com.frame.redis.IObjectRedisHelperService;
import com.frame.redis.IStringRedisHelperService;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * <p>定时器基础类</p>
 * @author FLY @date 2018-01-02<br>
 * @version 1.0<br>
 */
public abstract class BaseQuartz extends QuartzJobBean {
	/** 字符缓存实例 **/
	private IStringRedisHelperService stringRedisLogicBean = null;
	/** 本站缓存实例 **/
	private IObjectRedisHelperService objectRedisLogicBean = null;
	/** 日志书写对象 **/
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * <p>获取公有缓存操作工具实例</p>
	 * @return 公有缓存操作工具实例<br>
	 */
	public IStringRedisHelperService getStringRedisLogicBean() {
		if (CommHelper.isNull(stringRedisLogicBean)) {
			stringRedisLogicBean = ContextHelper.getBean(IStringRedisHelperService.class);
		}
		return stringRedisLogicBean;
	}
	
	/**
	 * <p>获取私有缓存操作工具实例</p>
	 * @return 公有缓存操作工具实例<br>
	 */
	public IObjectRedisHelperService getObjectRedisLogicBean() {
		if (CommHelper.isNull(objectRedisLogicBean)) {
			objectRedisLogicBean = ContextHelper.getBean(IObjectRedisHelperService.class);
		}
		return objectRedisLogicBean;
	}
}
