package com.qcgx.frame.utils.base;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.qcgx.frame.utils.file.FileHelper;

/**
 * <p>序列化通用工具类</p>
 * @author FLY @date 2017-10-19<br>
 * @version 1.0<br>
 */
public class SerializeHelper {
	/**
	 * <p>构造函数:禁止NEW创建</p>
	 */
	private SerializeHelper() {}
	
	/**
	 * <p>将指定对象序列化为字节数组</p>
	 * @param object 对象实例<br>
	 * @return 字节数组<br>
	 */
	public static byte[] serialize(Object object) {
		byte[] rtnBA = null;
		// 变量声明
		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object); rtnBA = bos.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(bos); FileHelper.closeIO(oos);
		}
		return rtnBA;
	}
	
	/**
	 * <p>将指定的字节数组序列化为对象</p>
	 * @param bytes 字节数组<br>
	 * @return 对象<br>
	 */
	public static Object unserialize(byte[] bytes) {
		Object rtnObj = null;
		// 变量声明
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			rtnObj = ois.readObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(bais);
		}
		return rtnObj;
	}
}
