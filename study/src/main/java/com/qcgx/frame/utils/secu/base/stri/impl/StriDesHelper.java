package com.qcgx.frame.utils.secu.base.stri.impl;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.log4j.Logger;
import com.qcgx.frame.utils.CommHelper;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import com.qcgx.frame.utils.base.RandomHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;

/**
 * <p>字符串DES加解密工具实现类</p>
 * @author FLY @date 2017-09-20<br>
 * @version 1.0<br>
 */
public class StriDesHelper extends StriBaseHelper {
	/** 日志书写对象 **/
	private static Logger logger = Logger.getLogger(StriDesHelper.class);
	
	/**
	 * <p>动态生成密匙</p>
	 */
	public String generateSecKey() {
		return RandomHelper.generateMixString(24);
	}
	
	/**
	 * <p>根据指定密匙明文字节数组进行加密</p>
	 */
	public byte[] encrypt(byte[] data, byte[] key) {
		byte[] rtnBA = null;
		// 合法性验证
		try {
			if (CommHelper.isNotEmptyByteArray(data) && CommHelper.isNotEmptyByteArray(key)) {
				// 初始化加密实例
				SecretKeyFactory keyFac = SecretKeyFactory.getInstance(type.getValue());
				SecretKey secretKey = keyFac.generateSecret(new DESKeySpec(key));
				// 执行加密操作
				Cipher cipher = Cipher.getInstance(type.getValue());
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom()); rtnBA = cipher.doFinal(data);
			}
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return rtnBA;
	}
	
	/**
	 * <p>根据指定密匙对密文字节数组进行解密</p>
	 */
	public byte[] decrypt(byte[] data, byte[] key) {
		byte[] rtnBA = null;
		try {
			// 合法性验证
			if (CommHelper.isNotEmptyByteArray(data) && CommHelper.isNotEmptyByteArray(key)) {
				// 初始化加密实例
				SecretKeyFactory keyFac = SecretKeyFactory.getInstance(type.getValue());
				SecretKey secretKey = keyFac.generateSecret(new DESKeySpec(key));
				// 执行加密操作
				Cipher cipher = Cipher.getInstance(type.getValue());
				cipher.init(Cipher.DECRYPT_MODE, secretKey, new SecureRandom()); rtnBA = cipher.doFinal(data);
			}
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return rtnBA;
	}
	
	/**
	 * <p>构造函数:初始化字串加密类型</p>
	 * @param type 字串加密类型<br>
	 */
	public StriDesHelper(EncryptTypeEnum type) {
		super(type);
	}
	
	/**
	 * <p>将字符串密匙转换为字节数组</p>
	 */
	public byte[] getSecKey(String key)  throws Exception {
		return key.getBytes(Constants.UTF_8);
	}
}
