package com.qcgx.frame.utils.secu.base.file.impl;

import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.secu.base.SecuHelper;
import com.qcgx.frame.utils.secu.base.file.FileEncrypt;
import com.qcgx.frame.utils.base.eum.EncryptTypeEnum;

/**
 * <p>文件加密基础抽象类</p>
 * @author FLY @date 2017-01-08<br>
 * @version 1.0<br>
 */
public abstract class FileBaseHelper implements FileEncrypt {
	/** 密匙文件存放路径 **/
	protected String keyPath;
	/** 加密文件存放路径 **/
	protected String destPath;
	/** 临时文件存放路径 **/
	protected String tempPath;
	/** 文件加密类型 **/
	protected EncryptTypeEnum type = null;
	
	/**
	 * <p>根据文件名清理DES加密环境</p>
	 */
	public void clearEncryptEnvironment(String name) {
		if (StringHelper.isNotEmpty(name)) {
			FileHelper.delete(SecuHelper.getLegalKeyFilePath(keyPath, name));
			FileHelper.delete(SecuHelper.getLegalTempFilePath(tempPath, name));
			FileHelper.delete(SecuHelper.getLegalEncryptFilePath(destPath, name));
		}
	}
	
	/**
	 * <p>构造函数:初始化相关参数</p>
	 * @param keyPath 密匙文件存放路径<br>
	 * @param destPath 加密文件存放路径<br>
	 * @param tempPath 临时文件存放路径<br>
	 * @param type 文件加密类型<br>
	 */
	protected FileBaseHelper(String keyPath, String destPath, String tempPath, EncryptTypeEnum type) {
		tempPath = StringHelper.isNotEmpty(tempPath) ? tempPath : System.getProperty("java.io.tmpdir");
		this.keyPath  = FileHelper.getLegalPath(keyPath);  FileHelper.creat(keyPath);
		this.destPath = FileHelper.getLegalPath(destPath); FileHelper.creat(destPath);
		this.tempPath = FileHelper.getLegalPath(tempPath); FileHelper.creat(tempPath); this.type = type;
	}
}
