package com.qcgx.frame.utils.secu;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import com.qcgx.frame.utils.CommHelper;
import java.security.MessageDigest;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.secu.base.SecuHelper;
import com.qcgx.frame.utils.secu.base.stri.impl.*;
import com.qcgx.frame.utils.secu.base.file.impl.*;
import java.security.NoSuchAlgorithmException;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;
import com.qcgx.frame.utils.base.eum.MessageDigestEnum;
import com.qcgx.frame.utils.secu.base.stri.StriEncrypt;
import com.qcgx.frame.utils.secu.base.file.FileEncrypt;

/**
 * <p>加解密通用工具操作类</p>
 * @author FLY @date 2017-05-31<br>
 * @version 1.0<br>
 */
@SuppressWarnings("restriction")
public class EncryptHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private EncryptHelper() {}
	
	/**
	 * <p>对字符串进行MD5加密</p>
	 * @param str 字符串<br>
	 * @return 加密字符串<br>
	 */
	public static String md5Encode(String str) {
		String rtnS = Constants.EMPTY_STRING;
		// 合法性验证
		try {
			MessageDigest temp = MessageDigest.getInstance("MD5");
			temp.update(StringHelper.getLegalString(str).getBytes());
			byte[] md = temp.digest();
			char strA[] = new char[md.length * 2]; int k = 0;
			for (int i = 0; i < md.length; i++) {
				byte byte0 = md[i];
				strA[k++] = Constants.HexDigits[byte0 >>> 4 & 0xf];
				strA[k++] = Constants.HexDigits[byte0 & 0xf];
			}
			rtnS = new String(strA);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>对字符串进行BASE64加密</p>
	 * @param str 字符串<br>
	 * @return 加密字符串<br>
	 */
	public static String base64Encode(String str) {
		return new BASE64Encoder().encode(StringHelper.getLegalString(str).getBytes());
	}
	
	/**
	 * <p>对字符串进行BASE64解密</p>
	 * @param str 加密字符串<br>
	 * @return 字符串<br>
	 */
	public static String base64Decode(String str) {
		String rtnO = Constants.EMPTY_STRING;
		try {
			rtnO = new String(new BASE64Decoder().decodeBuffer(StringHelper.getLegalString(str)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnO;
	}
	
	/** 字串加解密工具类实例化对象 **/
	private static StriEncrypt instanceStriDES = null, instanceStriAES = null, instanceStriPkcs5 = null;
	
	/**
	 * <p>以单态模式获取DES加密解密实例对象</p>
	 * @return 实例对象<br>
	 */
	public static synchronized StriEncrypt getDesInstance() {
		if (CommHelper.isNull(instanceStriDES)) {
			instanceStriDES = new StriDesHelper(EncryptTypeEnum.DES);
		}
		return instanceStriDES;
	}
	
	/**
	 * <p>以单态模式获取AES加密解密实例对象</p>
	 * @return 实例对象<br>
	 */
	public static synchronized StriEncrypt getAesInstance() {
		if (CommHelper.isNull(instanceStriAES)) {
			instanceStriAES = new StriAesHelper(EncryptTypeEnum.AES);
		}
		return instanceStriAES;
	}
	
	/**
	 * <p>以单态模式获取AES/PKCS5加密解密实例对象</p>
	 * @return 实例对象<br>
	 */
	public static synchronized StriEncrypt getPkcs5Instance() {
		if (CommHelper.isNull(instanceStriPkcs5)) {
			instanceStriPkcs5 = new StriPkcs5Helper(EncryptTypeEnum.AES);
		}
		return instanceStriPkcs5;
	}
	
	/**
	 * <p>以加密方式获取指定字符串的信息摘要</p>
	 * @param str 字符串<br>
	 * @param messageDigest 加密方式<br>
	 * @return 信息摘要字符串<br>
	 */
	public static String generateSummary(String str, MessageDigestEnum messageDigest) {
		String rtnS = Constants.EMPTY_STRING;
		try {
			// 合法性验证
			if (StringHelper.isNotEmpty(str)) {
				// 获取信息摘要实例对象
				MessageDigest digest = MessageDigest.getInstance(messageDigest.getValue());
				digest.update(str.getBytes()); rtnS = SecuHelper.getHexString(digest.digest());
			}
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>根据信息摘要验证指定字符串是否正常</p>
	 * @param summary 信息摘要<br>
	 * @param str 待验证字符串<br>
	 * @param messageDigest 消息摘要<br>
	 * @return boolean true:正常 false:异常<br>
	 */
	public static boolean checkSummary(String summary, String str, MessageDigestEnum messageDigest) {
		boolean rtnB = false;
		// 合法性验证
		if (StringHelper.isNotEmpty(str) && StringHelper.isNotEmpty(summary)) {
			try {
				MessageDigest digest = MessageDigest.getInstance(messageDigest.getValue());
				digest.update(str.getBytes());
				// 执行摘要与原信息校验
				rtnB = MessageDigest.isEqual(SecuHelper.getByteArray(summary), digest.digest());
			}  catch (java.security.NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}
		}
		return rtnB;
	}
	
	/** 文件加解密工具类实例化对象 **/
	private static FileEncrypt instanceFileDES = null, instanceFileAES = null, instanceFileRSA = null;
	
	/**
	 * <p>以单态模式获取AES加密解密实例对象</p>
	 * @param keyPath  密匙文件存放路径<br>
	 * @param destPath 加密文件存放路径<br>
	 * @param tempPath 临时文件存放路径<br>
	 * @return 实例对象<br>
	 */
	public static synchronized FileEncrypt getAesInstance(String keyPath, String destPath, String tempPath) {
		if (CommHelper.isNull(instanceFileAES)) {
			instanceFileAES = new FileAesHelper(keyPath, destPath, tempPath, EncryptTypeEnum.AES);
		}
		return instanceFileAES;
	}
	
	/**
	 * <p>以单态模式获取AES加密解密实例对象</p>
	 * @param keyPath  密匙文件存放路径<br>
	 * @param destPath 加密文件存放路径<br>
	 * @param tempPath 临时文件存放路径<br>
	 * @return 实例对象<br>
	 */
	public static synchronized FileEncrypt getRsaInstance(String keyPath, String destPath, String tempPath) {
		if (CommHelper.isNull(instanceFileRSA)) {
			instanceFileRSA = new FileRsaHelper(keyPath, destPath, tempPath, EncryptTypeEnum.RSA);
		}
		return instanceFileRSA;
	}
	
	/**
	 * <p>以单态模式获取DES加密解密实例对象</p>
	 * @param keyPath  密匙文件存放路径<br>
	 * @param destPath 加密文件存放路径<br>
	 * @param tempPath 临时文件存放路径<br>
	 * @return 实例对象<br>
	 */
	public static synchronized FileEncrypt getDesInstance(String keyPath, String destPath, String tempPath) {
		if (CommHelper.isNull(instanceFileDES)) {
			instanceFileDES = new FileDesHelper(keyPath, destPath, tempPath, EncryptTypeEnum.DES);
		}
		return instanceFileDES;
	}
}
