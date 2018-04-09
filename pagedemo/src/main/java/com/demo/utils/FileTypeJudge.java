package com.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import com.demo.utils.data.FileType;

/**
 * <p>文件类型判断类</p>
 * @author JL @date 2018-01-20<br>
 * @version 1.0<br>
 */
public final class FileTypeJudge {

	/**
	 * <p>构造函数</p>
	 */
	private FileTypeJudge() {
	}

	/**
	 * <p>将文件头转换成16进制字符串</p>
	 * @param src 原生byte<br>
	 * @return 16进制字符串<br>
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) return null;
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF; 
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) stringBuilder.append(0);
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * <p>得到文件头</p>
	 * @param filePath 文件路径<br>
	 * @return 文件头<br>
	 */
	private static String getFileContent(String filePath) {
		byte[] b = new byte[28];
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			inputStream.read(b, 0, 28);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytesToHexString(b);
	}

	/**
	 * <p>判断文件类型</p>
	 * @param filePath  文件路径<br>
	 * @return 文件类型<br>
	 */
	public static FileType getType(String filePath) {
		String fileHead = getFileContent(filePath);
		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}
		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
//		System.out.println(FileTypeJudge.getType("E:\\123.jpg"));
		System.out.println(FileTypeJudge.getType("http://marketcdn.dongzouxizou.com/youzan/home/671a58703e9245809784e4efc8608a561.png"));
	}
}
