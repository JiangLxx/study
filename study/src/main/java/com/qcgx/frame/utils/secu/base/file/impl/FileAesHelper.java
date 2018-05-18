package com.qcgx.frame.utils.secu.base.file.impl;

import java.io.*;
import javax.crypto.*;
import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.secu.base.SecuHelper;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;

/**
 * <p>文件AES加密方式通用工具类</p>
 * @author FLY @date 2017-01-08<br>
 * @version 1.0<br>
 */
public class FileAesHelper extends FileBaseHelper {
	/**
	 * <p>将指定路径的文件进行AES加密</p>
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
	 * <p>将指定文件名的文件进行AES解密</p>
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
	 * <p>以AES方式对字符串进行加密</p>
	 */
	public String encode(String str, String key) {
		String rtnS = Constants.EMPTY_STRING; Cipher cipher = null;
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(str) && StringHelper.isNotEmpty(key)) {
				// 执行AES加密
				cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, SecuHelper.generateSymKey(keyPath, key, type));
				rtnS = SecuHelper.getHexString(cipher.doFinal(str.getBytes())).toUpperCase();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>以AES方式对字符串进行解密</p>
	 */
	public String decode(String str, String key) {
		String rtnS = Constants.EMPTY_STRING; Cipher cipher = null;
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(str) && StringHelper.isNotEmpty(key)) {
				// 执行AES解密
				cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, SecuHelper.generateSymKey(keyPath, key, type));
				rtnS = new String(cipher.doFinal(SecuHelper.getByteArray(str)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	/**
	 * <p>将指定文件名的文件输入流进行AES加密</p>
	 */
	public void encode(String filename, InputStream input) {
		// 变量声明
		OutputStream output = null; CipherInputStream cis = null; Cipher cipher = null;
		try {
			// 执行文件加密
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, SecuHelper.generateSymKey(keyPath, filename, type));
			output = new FileOutputStream(SecuHelper.getLegalEncryptFilePath(destPath, filename));
			cis = new CipherInputStream(input, cipher);
			byte[] buffer = new byte[1024]; int length = 0;
			while((length = cis.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(cis); FileHelper.closeIO(input); FileHelper.closeIO(output);
		}
	}
	
	/**
	 * <p>将指定文件名的文件输入流进行AES解密</p>
	 */
	public String decode(String filename, InputStream input) {
		String path = Constants.EMPTY_STRING; Cipher cipher = null;
		// 变量声明
		OutputStream output = null; CipherOutputStream cos = null;
		try {
			// 执行文件解密
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, SecuHelper.generateSymKey(keyPath, filename, type));
			path = SecuHelper.getLegalTempFilePath(tempPath, filename);
			output = new java.io.FileOutputStream(path);
			cos = new CipherOutputStream(output, cipher);
			byte[] buffer = new byte[1024]; int length = 0;
			while((length = input.read(buffer)) >= 0) {
				cos.write(buffer, 0 , length);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(cos); FileHelper.closeIO(output); FileHelper.closeIO(input);
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
	public FileAesHelper(String keyPath, String destPath, String tempPath, EncryptTypeEnum type) {
		super(keyPath, destPath, tempPath, type);
	}
}
