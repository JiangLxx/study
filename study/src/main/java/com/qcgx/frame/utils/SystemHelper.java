package com.qcgx.frame.utils;

import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.secu.CheckHelper;
import com.qcgx.frame.utils.base.cnst.Constants;

/**
 * <p>基于系统操作的工具类</p>
 * @author FLY 2017-06-02<br>
 * @version 1.0<br>
 */
public class SystemHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private SystemHelper() {}
		
	/**
	 * <p>获取当前操作系统的名称</p>
	 * @return 系统名称<br>
	 */
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	/**
	 * <p>获取本机所有IP地址</p>
	 * @return IP地址数组<br>
	 */
	public static String[] getLocalIP() {
		String[] rtnSA = null;
		try {
			// IP地址收集容器
			List<String> result = new ArrayList<String>();
			// 循环遍历本机IP
			Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
			while (nifs.hasMoreElements()) {
				NetworkInterface nif = (NetworkInterface) nifs.nextElement();
				Enumeration<InetAddress> addresses = nif.getInetAddresses();
				while(addresses.hasMoreElements()) {
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (CommHelper.isNotNull(ip) && ip instanceof Inet4Address) {
						result.add(ip.getHostAddress());
					}
				}
			}
			if (CommHelper.isNotEmptyList(result)) rtnSA = result.toArray(new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnSA;
	}
	
	
	/**
	 * <p>获取本机MAC地址</p>
	 * @return MAC地址<br>
	 */
	public static String[] getLocalMac() {
		String[] rtnMac = null;
		try {
			// IP地址收集容器
			java.util.List<String> result = new java.util.ArrayList<String>();
			// 循环遍历本机IP
			Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
			while (nifs.hasMoreElements()) {
				NetworkInterface nif = (NetworkInterface) nifs.nextElement();
				if (CommHelper.isNotNull(nif) && nif.isUp()) {
					byte[] bytes = nif.getHardwareAddress();
					if (CommHelper.isNotEmptyByteArray(bytes) && bytes.length == 6) {
						StringBuffer sb = new StringBuffer();
						for (byte b : bytes) {
							sb.append(Integer.toHexString((b&240)>>4));
							sb.append(Integer.toHexString((b&15))); sb.append("-");
						}
						sb.deleteCharAt(sb.length() -1);
						result.add(sb.toString().toUpperCase());
					}
				}
			}
			rtnMac = result.toArray(new String[]{});
			if (CommHelper.isNotEmptyList(result)) rtnMac = result.toArray(new String[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnMac;
	}
	
	
	/**
	 * <p>获取JDK的版本号</p>
	 * @return 版本号<br>
	 */
	public static String getJdkVersion() {
		return System.getProperty("java.version");
	}
	
	/**
	 * <p>获取Windows平台下处理器序列号</p>
	 * @return 处理器序列号<br>
	 */
	public static String getWinProcessorId() {
		String rtnS = Constants.EMPTY_STRING;
		try {
			String[] commands = new String[]{"wmic", "cpu", "get", "ProcessorId"};
			Process process = Runtime.getRuntime().exec(commands);
			process.getOutputStream().close();
			Scanner sc = new Scanner(process.getInputStream());
			sc.next(); rtnS = sc.next(); sc.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
	
	
	/**
	 * <p>根据IP地址获取MAC地址</p>
	 * @param ip IP地址<br>
	 * @return MAC地址串<br>
	 */
	public static String getWinMac(String ip) {
		String rtnMac = Constants.EMPTY_STRING;
		// IO流声明
		BufferedReader br = null; InputStreamReader isr = null;
		try {
			if (CheckHelper.checkIP4(ip)) {
				Process process = Runtime.getRuntime().exec("nbtstat -A ".concat(ip));
				isr = new InputStreamReader(process.getInputStream(), Charset.forName("GBK"));
				br = new BufferedReader(isr);
				// 截取MAC地址串
				String line = null; int index = -1;
				while((line = br.readLine()) != null) {
					index = line.toLowerCase().indexOf("mac address");
					if (index >= 0) {
						rtnMac = line.substring(index + 14); break;
					}
					index = line.toLowerCase().indexOf("mac 地址");
					if (index >= 0) {
						rtnMac = line.substring(index + 9); break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(isr); FileHelper.closeIO(br);
		}
		return rtnMac;
	}
}
