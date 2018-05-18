package com.qcgx.frame.utils.secu.base.stri.impl;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.log4j.Logger;
import javax.crypto.KeyGenerator;
import com.qcgx.frame.utils.secu.CheckHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.secu.base.SecuHelper;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;
import com.qcgx.frame.utils.secu.base.stri.StriEncrypt;

/**
 * <p>字串加密基础抽象类</p>
 * @author FLY @date 2017-09-20<br>
 * @version 1.0<br>
 */
public abstract class StriBaseHelper implements StriEncrypt {
	/** 字串加密类型 **/
	protected EncryptTypeEnum type = null;
	/** 日志书写对象 **/
	private static Logger logger = Logger.getLogger(StriBaseHelper.class);
	
	/**
	 * <p>构造函数:初始化字串加密类型</p>
	 * @param type 字串加密类型<br>
	 */
	public StriBaseHelper(EncryptTypeEnum type) {
		this.type = type;
	}
	
	/**
	 * <p>动态生成密匙</p>
	 */
	public String generateSecKey() {
		String key = null;
		try {
			KeyGenerator generator = KeyGenerator.getInstance(type.getValue());
			generator.init(128); 
			key = new BASE64Encoder().encode(generator.generateKey().getEncoded());
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return key;
	}
	
	/**
	 * <p>根据指定密匙结合BASE64对字符串进行加密</p>
	 */
	public String encrypt_base64(String data, String key) {
		String rtnS = null;
		try {
			if (StringHelper.isNotEmpty(data) && CheckHelper.checkBase64(key)) {
				rtnS = new BASE64Encoder().encode(encrypt(data.getBytes(Constants.UTF_8), getSecKey(key)));
			}
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return rtnS;
	}

	/**
	 * <p>根据指定密匙结合BASE64对字符串进行解密</p>
	 */
	public String decrypt_base64(String data, String key) {
		String rtnS = null;
		try {
			if (CheckHelper.checkBase64(data) && CheckHelper.checkBase64(key)) {
				rtnS = new String(decrypt(new BASE64Decoder().decodeBuffer(data), getSecKey(key)), Constants.UTF_8);
			}
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return rtnS;
	}

	/**
	 * <p>根据指定密匙对字符串进行加密</p>
	 */
	public String encrypt_hexstring(String data, String key) {
		String rtnS = null;
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(data) && CheckHelper.checkBase64(key)) {
				rtnS = SecuHelper.getHexString(encrypt(data.getBytes(Constants.UTF_8), getSecKey(key)));
			}
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return rtnS;
	}

	/**
	 * <p>根据指定密匙对字符串进行解密</p>
	 */
	public String decrypt_hexstring(String data, String key) {
		String rtnS = null;
		try {
			if (CheckHelper.checkHex(data) && CheckHelper.checkBase64(key)) {
				rtnS = new String(decrypt(SecuHelper.getByteArray(data), getSecKey(key)), Constants.UTF_8);
			}
		} catch (Exception ex) {
			logger.error(ex); ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>将字符串密匙转换为字节数组</p>
	 */
	public byte[] getSecKey(String key) throws Exception {
		return  new BASE64Decoder().decodeBuffer(key);
	}
}
