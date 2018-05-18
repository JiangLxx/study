package com.qcgx.frame.utils.file;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.Lists;
import com.qcgx.frame.utils.base.StringHelper;

/**
 * <p>HTML的通用操作工具类</p>
 * @author FLY @date 2017-05-28<br>
 * @version 1.0<br>
 */
public class HtmlHelper {
	/**
	 * <p>构造函数:禁止以NEW方式创建对象</p>
	 */
	private HtmlHelper() { }
	
	/**
	 * <p>将指定的URL转换为HTML文档</p>
	 * @param url URL字符串<br>
	 * @param path 文档路径<br>
	 */
	public static void toHtml(String url, String path) {
		// 声明I/O流对象
		InputStream input = null; BufferedWriter writer = null; BufferedReader reader = null;
		try {
			// 创建新文件
			writer = new BufferedWriter(new FileWriter(new java.io.File(FileHelper.getLegalPath(path))));
			if (StringHelper.isNotEmpty(url)) {
				java.net.URLConnection connection = new java.net.URL(url).openConnection();
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				// 循环读取URL内容
				while(reader.ready()) {
					writer.write(reader.readLine());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(input); FileHelper.closeIO(reader); FileHelper.closeIO(writer);
		}
	}
	
	/**
	 * <p>获取HTML文本中指定标签的属性值</p>
	 * @param html HTML文本<br>
	 * @param element 标签名<br>
	 * @param attr 属性名<br>
	 */
	public static List<String> match(String html, String element, String attr) {
		List<String> rtnList = null;
		// 合法性判断
		if (StringHelper.isNotEmpty(html) && StringHelper.isNotEmpty(element) && StringHelper.isNotEmpty(attr)) {
			rtnList = Lists.newArrayList();
			// 组装匹配法则
			String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?\\s*(.*?)['\"]?(\\s.*?)?>";
			Matcher matcher = Pattern.compile(reg, Pattern.CASE_INSENSITIVE).matcher(html);
			// 执行标签匹配
			while (matcher.find()) {
				rtnList.add(matcher.group(1));
			}
		}
		return rtnList;
	}
	
	/**
	 * <p>读取HTML的模版并填充参数生成新的HTML文档:参数格式{XXXX}</p>
	 * @param template 模版文件路径<br>
	 * @param path 新HTML文档路径<br>
	 * @param params 替换的参数<br>
	 */
	public static void formatContent(String template, String path, Map<String, String> params) {
		// 声明I/O流对象
		java.io.FileInputStream input = null; java.io.BufferedReader  reader = null; java.io.FileOutputStream  output = null;
		try {
			if (FileHelper.isFile(template)) {
					StringBuffer contentBuf = new StringBuffer();
					input = new FileInputStream(FileHelper.getLegalPath(template));
					reader = new BufferedReader(new InputStreamReader(input));
					// 循环组装HTML内容
					String temp = "";
					while(StringHelper.isNotEmpty((temp = reader.readLine()))) {
						contentBuf.append(temp);
					}
					// 循环替换参数
					String content = contentBuf.toString();
					for (Map.Entry<String, String> map : params.entrySet()) {
						content = content.replace("{".concat(map.getKey()).concat("}"), map.getValue());
					}
					// 执行文件保存
					output = new FileOutputStream(new File(FileHelper.getLegalPath(path))); output.write(content.getBytes());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(input); FileHelper.closeIO(reader); FileHelper.closeIO(output);
		}
	}
}
