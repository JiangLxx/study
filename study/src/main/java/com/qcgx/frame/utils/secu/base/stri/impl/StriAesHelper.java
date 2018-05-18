package com.qcgx.frame.utils.secu.base.stri.impl;

import javax.crypto.Cipher;
import org.apache.log4j.Logger;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import com.qcgx.frame.utils.CommHelper;
import javax.crypto.spec.SecretKeySpec;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;

/**
 * <p>字符串AES加解密工具实现类</p>
 * @author FLY @date 2017-09-20<br>
 * @version 1.0<br>
 */
public class StriAesHelper extends StriBaseHelper {
	/** 日志书写对象 **/
	private static Logger logger = Logger.getLogger(StriAesHelper.class);
	
	/**
	 * <p>根据指定密匙明文字节数组进行加密</p>
	 */
	public byte[] encrypt(byte[] data, byte[] key) {
		byte[] rtnBA = null;
		try {
			if (CommHelper.isNotEmptyByteArray(data) && CommHelper.isNotEmptyByteArray(key)) {
				KeyGenerator keygen = KeyGenerator.getInstance(type.getValue());
				keygen.init(Constants.KEY_SIZE, new SecureRandom(key));
				byte[] keyBA = keygen.generateKey().getEncoded();
				// 根据字节数组生成AES密匙
				Cipher cipher = Cipher.getInstance(type.getValue());
				cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBA, type.getValue()));
				rtnBA = cipher.doFinal(data);
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
			if (CommHelper.isNotEmptyByteArray(data) && CommHelper.isNotEmptyByteArray(key)) {
				KeyGenerator keygen = KeyGenerator.getInstance(type.getValue());
				keygen.init(Constants.KEY_SIZE, new SecureRandom(key));
				byte[] keyBA = keygen.generateKey().getEncoded();
				// 根据字节数组生成AES密匙
				Cipher cipher = Cipher.getInstance(type.getValue());
				cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBA, type.getValue()));
				rtnBA = cipher.doFinal(data);
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
	public StriAesHelper(EncryptTypeEnum type) {
		super(type);
	}
}
