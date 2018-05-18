package com.qcgx.frame.utils.file;

import java.io.*;
import java.util.*;
import java.math.*;
import com.qcgx.frame.utils.base.*;

/**
 * <p>基于文件的通用操作工具类</p>
 * @author FLY @date 2017-05-28<br>
 * @version 1.0<br>
 */
public class PropsHelper {
	/** 文档路径 **/
	private String path = null;
	/** 文件对象 **/
	private Properties props = null;
	
	/**
	 * <p>构造函数:禁止以NEW方式创建对象</p>
	 * @param path 文档路径<br>
	 */
	private PropsHelper(String path) {
		InputStream input = null;
		try {
			this.path = path;
			if (FileHelper.isFile(path) == false) {
				props = new Properties();
			} else {
				path = FileHelper.getLegalPath(path);
				input = new FileInputStream(path); props.load(input);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(input);
		}
	}
	
	/**
	 * <p>保存指定的文件</p>
	 * @param comment 注释信息<br>
	 */
	public void save(String comment) {
		java.io.OutputStream output = null;
		try {
			path = FileHelper.getLegalPath(path);
			if (FileHelper.isFile(path) == false)
				FileHelper.creat(path);
			output = new FileOutputStream(path);
			props.store(output, comment);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(output);
		}
	}
	
	/**
	 * <p>根据关键字获取字节类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Byte}属性值<br>
	 */
	public byte getByte(String key) {
		return NumberHelper.getByte(props.getProperty(key));
	}
	
	/**
	 * <p>根据关键字获取长整型类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Long}属性值<br>
	 */
	public long getLong(String key) {
		return NumberHelper.getLong(props.getProperty(key));
	}
	
	/**
	 * <p>根据关键字获取整型类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Integer}}属性值<br>
	 */
	public int getInteger(String key) {
		return NumberHelper.getInteger(props.getProperty(key));
	}
	
	/**
	 * <p>根据关键字获取短整型类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Short}}属性值<br>
	 */
	public short getShort(String key) {
		return NumberHelper.getShort(props.getProperty(key));
	}
	
	/**
	 * <p>根据关键字获取浮点类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Float}属性值<br>
	 */
	public float getFloat(String key) {
		return NumberHelper.getFloat(props.getProperty(key));
	}
	
	/**
	 * <p>根据关键字获取双精度类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Double}属性值<br>
	 */
	public double getDouble(String key) {
		return NumberHelper.getDouble(props.getProperty(key));
	}
	
	/**
	 * <p>根据关键字获取布尔值类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.lang.Boolean}属性值<br>
	 */
	public boolean getBoolean(String key) {
		return BooleanHelper.getBoolean(props.getProperty(key));
	}
	
	/**
	 * <p>移除指定属性及值</p>
	 * @param key 关键字<br>
	 */
	public void removeAttribute(String key) {
		if (props.containsKey(key)) props.remove(key);
	}
	
	/**
	 * <p>根据关键字获取大数据类型属性值</p>
	 * @param key 关键字<br>
	 * @return {@link java.math.BigDecimal}属性值<br>
	 */
	public BigDecimal getBigDecimal(String key) {
		return NumberHelper.getBigDecimal(props.getProperty(key));
	}
	
	/**
	 * <p>增加属性及值</p>
	 * @param key 关键字<br>
	 * @param value 属性值<br>
	 */
	public void addAttribute(String key, String value) {
		if (StringHelper.isNotEmpty(key) && StringHelper.isNotEmpty(value)) props.put(key, value);
	}
	
	/**
	 * <p>获取文件操作实例</p>
	 * @param path 文件路径<br>
	 * @return 操作实例<br>
	 */
	public static synchronized PropsHelper getInstance(String path) {
		return new PropsHelper(path);
	}
}
