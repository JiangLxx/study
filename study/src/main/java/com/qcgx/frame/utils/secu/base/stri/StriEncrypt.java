package com.qcgx.frame.utils.secu.base.stri;

/**
 * <p>字符串加密接口定义类</p>
 * @author FLY @date 2017-09-20<br>
 * @version 1.0<br>
 */
public interface StriEncrypt {
	/**
	 * <p>动态生成密匙</p>
	 * @return 密匙串<br>
	 */
	public String generateSecKey();
	
	/**
	 * <p>根据指定密匙明文字节数组进行加密</p>
	 * @param data 明文字节数组<br>
	 * @param key  密匙字节数组<br>
	 * @return 密文字节数组<br>
	 */
	public byte[] encrypt(byte[] data, byte[] key);
	
	/**
	 * <p>根据指定密匙对密文字节数组进行解密</p>
	 * @param data 密文字节数组<br>
	 * @param key  密匙字节数组<br>
	 * @return 明文字节数组<br>
	 */
	public byte[] decrypt(byte[] data, byte[] key);
	
	/**
	 * <p>根据指定密匙结合BASE64对字符串进行加密</p>
	 * @param data 明文字符串<br>
	 * @param key 密匙字符串<br>
	 * @return 密文字符串<br>
	 */
	public String encrypt_base64(String data, String key);
	
	/**
	 * <p>根据指定密匙结合BASE64对字符串进行解密</p>
	 * @param data 密文字符串<br>
	 * @param key 密匙字符串<br>
	 * @return 明文字符串<br>
	 */
	public String decrypt_base64(String data, String key);
	
	/**
	 * <p>根据指定密匙对字符串进行加密</p>
	 * @param data 明文字符串<br>
	 * @param key 密匙字符串<br>
	 * @return 十六进制密文字符串<br>
	 */
	public String encrypt_hexstring(String data, String key);
	
	/**
	 * <p>根据指定密匙对字符串进行解密</p>
	 * @param data 十六进制密文字符串<br>
	 * @param key 密匙字符串<br>
	 * @return 明文字符串<br>
	 */
	public String decrypt_hexstring(String data, String key);
	
	/**
	 * <p>将字符串密匙转换为字节数组</p>
	 * @param String 字符串密匙<br>
	 * @return 字节数组<br>
	 * @throws Exception 异常对象<br>
	 */
	public byte[] getSecKey(String String) throws Exception;
}
