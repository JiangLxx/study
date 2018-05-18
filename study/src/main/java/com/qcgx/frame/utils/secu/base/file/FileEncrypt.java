package com.qcgx.frame.utils.secu.base.file;

import java.io.InputStream;

/**
 * <p>文件加密接口定义类</p>
 * @author FLY @date 2017-01-08<br>
 * @version 1.0<br>
 */
public interface FileEncrypt {
	/**
	 * <p>将指定路径的文件进行AES加密</p>
	 * @param filepath 源文件<br>
	 */
	public void encode(String filepath);
	
	/**
	 * <p>将指定文件名的文件进行解密</p>
	 * @param filename 文件名<br>
	 * @return 解密文件路径<br>
	 */
	public String decode(String filename);
	
	/**
	 * <p>对字符串进行加密<p>
	 * @param str 字符串<br>
	 * @param key AES密匙<br>
	 * @return 加密字符串<br>
	 */
	public String encode(String str, String key);
	
	/**
	 * <p>对字符串进行解密</p>
	 * @param str 字符串<br>
	 * @param key AES密匙<br>
	 * @return 解密字符串<br>
	 */
	public String decode(String str, String key);
	
	/**
	 * <p>根据文件名清理加密环境</p>
	 * @param name 文件名称<br>
	 */
	public void clearEncryptEnvironment(String name);
	
	/**
	 * <p>将指定文件名的文件输入流进行AES加密</p>
	 * @param filename 源文件名<br>
	 * @param input 文件输入流对象<br>
	 */
	public void encode(String filename, InputStream input);
	
	/**
	 * <p>将指定文件名的文件输入流进行解密</p>
	 * @param filename 源文件名<br>
	 * @param input 文件输入流<br>
	 * @return 解密文档路径<br>
	 */
	public String decode(String filename, InputStream input);
}
