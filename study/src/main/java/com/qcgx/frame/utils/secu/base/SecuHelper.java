package com.qcgx.frame.utils.secu.base;

import java.io.*;
import java.security.*;
import javax.crypto.KeyGenerator;
import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.secu.EncryptHelper;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;

/**
 * <p>加密解密辅助工具类</p>
 * @author FLY @date 2017-01-08<br>
 * @version 1.0<br>
 */
public class SecuHelper {
	/**
	 * <p>将字节数组转换为十六进制字符串</p>
	 * @param array 字节数组<br>
	 * @return 十六进制字符串<br>
	 */
	public static String getHexString(byte[] array) {
		String rtnS = Constants.EMPTY_STRING;
		// 合法性判断
		if (CommHelper.isNotEmptyByteArray(array)) {
			String temp = Constants.EMPTY_STRING;
			// 循环转换
			for(int i =0; i < array.length; i ++) {
				temp = (Integer.toHexString(array[i] & 0XFF));
				if (temp.length() == 1) {
					rtnS += "0".concat(temp);
				} else {
					rtnS += temp;
				}
			}
		}
		return rtnS;
	}
	
	/**
	 * <p>将十六进制字符串转换为字节数组</p>
	 * @param hexstr 十六进制字符串<br>
	 * @return 字节数组<br>
	 */
	public static byte[] getByteArray(String hexstr) {
		byte[] rtnBA = null;
		// 合法性判断
		if (StringHelper.isNotEmpty(hexstr)) {
			String temp = Constants.EMPTY_STRING;
			// 将摘要转换为字节数组
			rtnBA = new byte[hexstr.length() / 2];
			// 循环所有字符
			for (int i = 0; i < rtnBA.length; i ++) {
				temp = hexstr.substring(i * 2, i * 2 + 2);
				rtnBA[i] = (byte)Integer.parseInt(temp, 16);
			}
		}
		return rtnBA;
	}
	
	/**
	 * <p>获取合法的解密文件路径</p>
	 * @param path 路径字符串<br>
	 * @param name 解密文件名<br>
	 * @return 解密文件路径<br>
	 */
	public static String getLegalTempFilePath (String path, String name) {
		return FileHelper.concat(path, StringHelper.getLegalString(name));
	}
	
	/**
	 * <p>获取合法的密匙文件路径</p>
	 * @param path 路径字符串<br>
	 * @param keyname 密匙文件名<br>
	 * @return 密匙文件路径<br>
	 */
	public static String getLegalKeyFilePath(String path, String keyname) {
		return FileHelper.concat(path, EncryptHelper.md5Encode(keyname).concat(".key"));
	}
	
	/**
	 * <p>获取合法的加密文件路径</p>
	 * @param path 路径字符串<br>
	 * @param keyname 加密文件名称<br> 
	 * @return 加密文件路径<br>
	 */
	public static String getLegalEncryptFilePath(String path, String keyname) {
		return FileHelper.concat(path, EncryptHelper.md5Encode(keyname).concat(".data"));
	}
	
	/**
	 * <p>根据密匙文件名获取非对称密匙文件对象</p>
	 * @param keypath 密匙路径<br>
	 * @param keyname 密匙文件名<br>
	 * @param type 加密方式<br>
	 * @return 密匙对象<br>
	 */
	public static KeyPair generateNonSymKey(String keypath, String keyname, EncryptTypeEnum type) {
		KeyPair rtnKey = null; KeyPairGenerator keyGen = null;
		// 变量声明
		InputStream keyInput = null; ObjectInputStream keyOis = null;
		FileOutputStream keyFos = null; ObjectOutputStream keyOos = null;
		try {
			// 密匙文件路径
			String keyFile = SecuHelper.getLegalKeyFilePath(keypath, keyname);
			// 获取密匙文件
			if (FileHelper.isFile(keyFile)) {
				keyInput = new FileInputStream(keyFile);
				keyOis = new ObjectInputStream(keyInput);
				rtnKey = (KeyPair) keyOis.readObject();
			} else {
				if (EncryptTypeEnum.RSA.equals(type)) {
					keyGen = KeyPairGenerator.getInstance("RSA");
				}
				keyGen.initialize(1024);
				rtnKey = keyGen.generateKeyPair();
				keyFos = new FileOutputStream(keyFile);
				keyOos = new ObjectOutputStream(keyFos);
				keyOos.writeObject(rtnKey);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(keyOos); FileHelper.closeIO(keyFos);
			FileHelper.closeIO(keyOis); FileHelper.closeIO(keyInput);
		}
		return rtnKey;
	}
	
	/**
	 * <p>根据密匙文件名获取对称密匙文件对象</p>
	 * @param keypath 密匙路径<br>
	 * @param keyname 密匙文件名<br>
	 * @param type 加密方式<br>
	 * @return 密匙对象<br>
	 */
	public static Key generateSymKey(String keypath, String keyname, EncryptTypeEnum type) {
		Key rtnKey = null; KeyGenerator keyGen = null;
		// 变量声明
		InputStream keyInput = null; ObjectInputStream keyOis = null;
		FileOutputStream keyFos = null; ObjectOutputStream keyOos = null;
		try {
			// 密匙文件路径
			String keyFile = SecuHelper.getLegalKeyFilePath(keypath, keyname);
			// 获取密匙文件
			if (FileHelper.isFile(keyFile)) {
				keyInput = new FileInputStream(keyFile);
				keyOis = new ObjectInputStream(keyInput);
				rtnKey = (Key) keyOis.readObject();
			} else {
				if (EncryptTypeEnum.DES.equals(type)) {
					keyGen = KeyGenerator.getInstance("DES");
				}
				else if (EncryptTypeEnum.AES.equals(type)) {
					keyGen = KeyGenerator.getInstance("AES");
				}
				else if (EncryptTypeEnum.DES3.equals(type)) {
					keyGen = KeyGenerator.getInstance("DESede");
				}
				keyGen.init(new SecureRandom());
				rtnKey = keyGen.generateKey();
				keyFos = new FileOutputStream(keyFile);
				keyOos = new ObjectOutputStream(keyFos);
				keyOos.writeObject(rtnKey);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(keyOos); FileHelper.closeIO(keyFos);
			FileHelper.closeIO(keyOis); FileHelper.closeIO(keyInput);
		}
		return rtnKey;
	}
}
