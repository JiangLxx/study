package com.qcgx.frame.utils.secu.base.file.impl;

import java.io.*;
import javax.crypto.*;
import java.security.KeyPair;
import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.secu.base.SecuHelper;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;

/**
 * <p>文件RSA加密方式通用工具类</p>
 * @author FLY @date 2017-01-08<br>
 * @version 1.0<br>
 */
public class FileRsaHelper extends FileBaseHelper {
	/**
	 * <p>将指定路径的文件进行RSA加密</p>
	 */
	public void encode(String filepath) {
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(filepath)) {
				File file = new File(FileHelper.getLegalPath(filepath));
				if (FileHelper.isFile(file)) {
					encode(file.getName(), new FileInputStream(file));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>将指定文件名的文件进行RSA解密</p>
	 */
	public String decode(String filename) {
		String path = Constants.EMPTY_STRING;
		try {
			String keydata = SecuHelper.getLegalEncryptFilePath(destPath, filename);
			path = decode(filename, new FileInputStream(new File(keydata)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return path;
	}
	
	/**
	 * <p>以RSA方式对字符串进行加密</p>
	 */
	public String encode(String str, String key) {
		String rtnS = Constants.EMPTY_STRING;
		try {
			// 变量声明
			Cipher cipher = null; KeyPair pairKey = null;
			// 合法性判断
			if (StringHelper.isNotEmpty(str) && StringHelper.isNotEmpty(key)) {
				// 执行RSA加密
				cipher = Cipher.getInstance("RSA");
				pairKey = SecuHelper.generateNonSymKey(keyPath, key, type);
				cipher.init(Cipher.ENCRYPT_MODE, pairKey.getPublic());
				rtnS = SecuHelper.getHexString(cipher.doFinal(str.getBytes())).toUpperCase();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>以RSA方式对字符串进行解密</p>
	 */
	public String decode(String str, String key) {
		String rtnS = Constants.EMPTY_STRING;
		try {
			// 变量声明
			Cipher cipher = null; KeyPair pairKey = null;
			// 合法性判断
			if (StringHelper.isNotEmpty(str) && StringHelper.isNotEmpty(key)) {
				// 执行RSA解密
				cipher = Cipher.getInstance("RSA");
				pairKey = SecuHelper.generateNonSymKey(keyPath, key, type);
				cipher.init(Cipher.DECRYPT_MODE, pairKey.getPrivate());
				rtnS = new String(cipher.doFinal(SecuHelper.getByteArray(str)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>将指定文件名的文件输入流进行RSA加密</p>
	 */
	public void encode(String filename, InputStream input) {
		// 变量声明
		OutputStream output = null; KeyPair pairKey = null;
		BufferedInputStream bis = null; Cipher cipher = null;
		try {
			// 初始加密环境
			cipher = Cipher.getInstance("RSA");
			pairKey = SecuHelper.generateNonSymKey(keyPath, filename, type);
			cipher.init(Cipher.ENCRYPT_MODE, pairKey.getPublic());
			output = new FileOutputStream(SecuHelper.getLegalEncryptFilePath(destPath, filename));
			// 执行分段加密
			bis = new BufferedInputStream(input); int length = 0;
			byte[] buffer = new byte[Constants.MAX_ENCRYPT_BLOCK];
			while((length = bis.read(buffer, 0, Constants.MAX_ENCRYPT_BLOCK)) > 0) {
				output.write(cipher.doFinal(buffer, 0, length));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(bis); FileHelper.closeIO(input); FileHelper.closeIO(output);
		}
	}
	
	/**
	 * <p>将指定文件名的文件输入流进行RSA解密</p>
	 */
	public String decode(String filename, InputStream input) {
		String path = Constants.EMPTY_STRING; Cipher cipher = null;
		// 变量声明
		OutputStream output = null; BufferedInputStream bis = null; KeyPair pairKey = null;
		try {
			// 执行解密
			cipher = Cipher.getInstance("RSA");
			pairKey = SecuHelper.generateNonSymKey(keyPath, filename, type);
			cipher.init(Cipher.DECRYPT_MODE, pairKey.getPrivate());
			// 执行分段解密
			path = SecuHelper.getLegalTempFilePath(tempPath, filename);
			output = new java.io.FileOutputStream(path);
			// 执行文件解密
			bis = new BufferedInputStream(input); int length = 0;
			byte[] buffer = new byte[Constants.MAX_DECRYPT_BLOCK];
			while((length = bis.read(buffer, 0, Constants.MAX_DECRYPT_BLOCK)) > 0) {
				output.write(cipher.doFinal(buffer, 0, length));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(bis); FileHelper.closeIO(output); FileHelper.closeIO(input);
		}
		return path;
	}
	
	/**
	 * <p>构造函数:初始化相关参数</p>
	 * @param keyPath 密匙文件存放路径<br>
	 * @param destPath 加密文件存放路径<br>
	 * @param tempPath 临时文件存放路径<br>
	 * @param type 文件加密类型<br>
	 */
	public FileRsaHelper(String keyPath, String destPath, String tempPath, EncryptTypeEnum type) {
		super(keyPath, destPath, tempPath, type);
	}
}
