package com.demo.utils;

import java.net.URL;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import java.net.URLConnection;
import java.io.BufferedReader;
import org.slf4j.LoggerFactory;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.idiot.utils.CommHelper;
import java.net.HttpURLConnection;
import org.apache.http.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.idiot.utils.file.FileHelper;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;
import com.idiot.utils.base.StringHelper;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 * <p>HTTP服务通用工具类</p>
 * @author FLY @date 2018-01-16<br>
 * @version 1.0<br>
 */
public class HttpHelper {
	/** 日志书写实例 **/
	private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
	
	/**
	 * <p>将哈希对象转换为请求参数</p>
	 * @param params 哈希对象<br>
	 * @return 请求参数<br>
	 */
	public static String getParamter(Map<String, String> params) {
		String rtnStr = null;
		// 合法性判断
		if (CommHelper.isNotEmptyMap(params)) {
			StringBuffer buffer = new StringBuffer(); int index = 0;
			for(Map.Entry<String, String> entry : params.entrySet()) {
				if (index == 0) {
					buffer.append(entry.getKey()).append("=").append(entry.getValue());
				} else {
					buffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
			}
			rtnStr = buffer.toString();
		}
		return rtnStr;
	}
	
	/**
	 * <p>对内容进行URL编码</p>
	 * @param source 编码内容<br>
	 * @param charset 字符编码<br> 
	 * @return 编码字符串<br>
	 */
	public static String urlEncode(String source, String charset) {
		String result = "";
		try {
			result = java.net.URLEncoder.encode(source, charset);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>对内容进行URL解码</p>
	 * @param source 解码内容<br>
	 * @param charset 字符编码<br>
	 * @return 解码字符串<br>
	 */
	public static String urlDecode(String source, String charset) {
		String result = "";
		try {
			result = java.net.URLDecoder.decode(source, charset);
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>发送GET请求</p>
	 * @param url 请求地址<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param charset 字符编码<br>
	 * @return 请求结果<br>
	 */
	public static String sendGet(String url, Map<String, String> params, Map<String,String> header, String charset) {
		String result = ""; URL request = null; BufferedReader reader = null;
		try {
			// 合法性验证
			if (StringHelper.isNotEmpty(url)) {
				String param = getParamter(params);
				// 组装查询条件
				if (StringHelper.isEmpty(param)) {
					request = new URL(url);
				} else {
					request = new URL(url.concat("?").concat(param));
				}
				// 打开链接
				URLConnection connection = request.openConnection();
				// 请求属性
				if (CommHelper.isNotEmptyMap(header)) {
					for(Map.Entry<String, String> entry : header.entrySet()) {
						connection.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}
				// 执行请求
				connection.connect(); StringBuffer buffer = new StringBuffer();
				// 读取结果
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
				String line = "";
				while((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				result = buffer.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		} finally {
			FileHelper.closeIO(reader);
		}
		return result;
	}
	
	/**
	 * <p>发送POST请求</p>
	 * @param url 请求地址<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param charset 字符编码<br>
	 * @return 请求结果<br>
	 */
	public static String sendPost(String url, Map<String, String> params, Map<String,String> header, String charset) {
		String result = null; BufferedReader reader = null; OutputStreamWriter osw = null;
		try {
			// 合法性验证
			if (StringHelper.isNotEmpty(url)) {
				URL request = new URL(url);
				// 打开链接
				URLConnection connection = request.openConnection();
				// 设置表头
				if (CommHelper.isNotEmptyMap(header)) {
					for(Map.Entry<String, String> entry : header.entrySet()) {
						connection.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}
				// 发送请求
				connection.setDoOutput(true); connection.setDoInput(true);
				// 发送参数
				osw = new OutputStreamWriter(connection.getOutputStream(), charset);
				osw.write(getParamter(params)); osw.flush();
				// 读取结果
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
				StringBuffer buffer = new StringBuffer(); String line = "";
				while((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				result = buffer.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		} finally {
			FileHelper.closeIO(osw); FileHelper.closeIO(reader);
		}
		return result;
	}

	/**
	 * <p>使用HttpURLConnection发送GET请求</p>
	 * @param url 请求路径<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param charset 字符编码<br>
	 * @return 返回结果<br>
	 */
	public static String sendHttpGet(String url, Map<String, String> params, Map<String,String> header, String charset) {
		String result = null; URL request = null; HttpURLConnection connection = null; BufferedReader reader = null;
		try {
			// 合法性验证
			if (StringHelper.isNotEmpty(url)) {
				String param = getParamter(params);
				// 组装查询条件
				if (StringHelper.isEmpty(param)) {
					request = new URL(url);
				} else {
					request = new URL(url.concat("?").concat(param));
				}
				// 打开链接
				connection= (HttpURLConnection)request.openConnection();
				// 请求属性
				if (CommHelper.isNotEmptyMap(header)) {
					for(Map.Entry<String, String> entry : header.entrySet()) {
						connection.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}
				// 执行请求
				connection.connect();
				// 读取结果
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
				StringBuffer buffer = new StringBuffer(); String line = "";
				while((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				result = buffer.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		} finally {
			FileHelper.closeIO(reader); if (CommHelper.isNotNull(connection)) connection.disconnect();
		}
		return result;
	}
	
	/**
	 * <p>使用HttpURLConnection发送POST请求</p>
	 * @param url 请求路径<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param charset 字符编码<br>
	 * @return 请求结果<br>
	 */
	public static String sendHttpPost(String url, Map<String, String> params, Map<String, String> header, String charset) {
		String result = null; HttpURLConnection connection = null; OutputStreamWriter osw = null; BufferedReader reader = null;
		try {
			URL request = new URL(url);
			// 打开链接
			connection = (HttpURLConnection) request.openConnection();
			connection.setRequestMethod("POST"); connection.setDoOutput(true);
			connection.setDoInput(true); connection.setUseCaches(false);
			// 设置报头
			if (CommHelper.isNotEmptyMap(header)) {
				for(Map.Entry<String, String> entry : header.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			osw = new OutputStreamWriter(connection.getOutputStream(), charset);
			osw.write(getParamter(params)); osw.flush();
			// 读取结果
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line = null; StringBuffer buffer = new StringBuffer();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			result = buffer.toString();
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		} finally {
			FileHelper.closeIO(reader); FileHelper.closeIO(osw);
			if(CommHelper.isNotNull(connection)) connection.disconnect();
		}
		return result;
	}
	
	/**
	 * <p>运用HttpClient工具发送POST请求</p>
	 * @param url 请求路径<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param charset 字符编码<br>
	 * @return 返回结果<br>
	 */
	public static JSONObject httpClientPost(String url, JSONObject params, Map<String, String> header, String charset) {
		JSONObject rtnJson = null; HttpClient httpClient = null; HttpPost request = null;
		try {
			// 合法性验证
			if (StringHelper.isNotEmpty(url)) {
				httpClient = HttpClients.createDefault(); request = new HttpPost(url);
				// 设置报头
				if (CommHelper.isNotEmptyMap(header)) {
					for(Map.Entry<String, String> entry : header.entrySet()) {
						request.setHeader(entry.getKey(), entry.getValue());
					}
				}
				// 设置参数
				if (CommHelper.isNotNull(params)) {
					StringEntity entity = new StringEntity(params.toString(), Charset.forName(charset));
					entity.setContentEncoding(charset); entity.setContentType("application/json"); request.setEntity(entity);
				}
				// 发送请求
				HttpResponse response = httpClient.execute(request);
				logger.debug(JSONObject.toJSONString(response));
				// 检验返回
				if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
					rtnJson = JSONObject.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnJson;
	}
	
	/**
	 * <p>运用HttpClient工具发送GET请求</p>
	 * @param url 请求路径<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param hasRes 响应标识<br>
	 * @return 返回结果<br>
	 */
	public static JSONObject httpClientGet(String url, Map<String, String> params, Map<String, String> header, boolean hasRes) {
		JSONObject rtnJson = null; HttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault(); HttpGet httpGet = null;
			// 设置参数
			if (CommHelper.isNotEmptyMap(params)) {
				String param = getParamter(params);
				// 组装查询条件
				if (StringHelper.isEmpty(param)) {
					httpGet = new HttpGet(url);
				} else {
					httpGet = new HttpGet(url.concat("?").concat(param));
				}
			}
			// 发送请求
			HttpResponse response = httpClient.execute(httpGet);
			logger.debug(JSONObject.toJSONString(response));
			// 返回结果
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				String result = EntityUtils.toString(response.getEntity());
				if (hasRes) {
					rtnJson = JSONObject.parseObject(result);
				}
			}
			
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnJson;
	}
	
	/**
	 * <p>运用HttpClient工具发送POST请求</p>
	 * @param url 请求路径<br>
	 * @param params 请求参数<br>
	 * @param header 报头参数<br>
	 * @param hasRes 响应标识<br>
	 * @return 返回结果<br>
	 */
	public static JSONObject httpClientPost(String url, Map<String, String> params, Map<String, String> header, boolean hasRes) {
		JSONObject rtnJson = null; List<NameValuePair> paramList = null; HttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault(); HttpPost httpPost = new HttpPost(url);
			// 设置报头
			if (CommHelper.isNotEmptyMap(header)) {
				for(Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			if (CommHelper.isNotEmptyMap(params)) {
				paramList = Lists.newArrayList();
				for(Map.Entry<String, String> entry : params.entrySet()) {
					paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(paramList));
			}
			// 发送请求
			HttpResponse response = httpClient.execute(httpPost);
			logger.debug(JSONObject.toJSONString(response));
			// 返回结果
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				if (hasRes) {
					rtnJson = JSONObject.parseObject(result);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage()); ex.printStackTrace();
		}
		return rtnJson;
	}
}
