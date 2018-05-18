package com.qcgx.frame.utils.dbs;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.qcgx.frame.utils.base.*;
import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.dbs.eum.DatabaseEnum;

/**
 * 基于SQL的通用操作工具类<p>
 * @author FLY @date 2017-06-02<br>
 * @version 1.0<br>
 */
public class SQLHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private SQLHelper() {}
	
	/**
	 * 将系统日期转换为数据库日期<p>
	 * @param date 系统日期<br>
	 * @return 数据库日期<br>
	 */
	public static Timestamp getTimestamp(java.util.Date date) {
		return CommHelper.isNull(date) ? null : new Timestamp(date.getTime());
	}
	
	/**
	 * 将字符串数组拼装为参数字符串<p>
	 * @param array 数组<br>
	 * @param delimiter 分隔符标识<br>
	 * @return SQL参数字符串<br>
	 */
	public static String getParameter(String[] array, boolean delimiter) {
		String rtnS = Constants.EMPTY_STRING;
		// 合法性判断
		if (CommHelper.isNotEmptyArray(array)) {
			StringBuffer temp = new StringBuffer();
			// 循环组装
			for(int i = 0; i < array.length; i ++) {
				if (i == 0) {
					temp  = delimiter ? temp.append("'").append(array[i]).append("'") : temp.append(array[i]);
				} else {
					temp  = delimiter ? temp.append(",'").append(array[i]).append("'") : temp.append(",").append(array[i]);
				}
			}
			rtnS = temp.toString();
		}
		return rtnS;
	}
	
	/**
	 * 根据数据库类型将日期对象转化为数据库日期字符串<p>
	 * @param date 日期对象<br>
	 * @param db 数据库枚举对象<br>
	 * @return 数据库日期字符串<br>
	 */
	public static String getDateTime(java.util.Date date, DatabaseEnum db) {
		String rtnStr = Constants.EMPTY_STRING;
		// 合法性判断
		if (CommHelper.isNotNull(date) && CommHelper.isNotNull(db)) {
			// 根据数据库类型返回
			if (DatabaseEnum.ORACLE.equals(db)) {
				rtnStr = "TO_DATE('".concat(DateHelper.format(date, "yyyy-MM-dd HH:mm:ss")).concat("','yyyy-mm-dd hh24:mi:ss')");
			}
			// DB2数据库
			else if(DatabaseEnum.DB2.equals(db)) {
				rtnStr = "TIMESTAMP('".concat(DateHelper.format(date, "yyyy-MM-dd HH:mm:ss")).concat("')");
			}
			// MYSQL数据库
			else if (DatabaseEnum.MYSQL.equals(db)) {
				rtnStr = "STR_TO_DATE('".concat(DateHelper.format(date, "yyyy-MM-dd HH:mm:ss")).concat("','%Y-%m-%d %h:%i:%s')");
			}
			// MISSQL数据库
			else if (DatabaseEnum.MISSQL.equals(db)) {
				rtnStr = "CAST('".concat(DateHelper.format(date, "yyyy-MM-dd HH:mm:ss")).concat("' AS DATETIME)");
			}
		}
		return rtnStr;
	}
	
	/**
	 * 从SQL文件中加载指定的SQL语句<p>
	 * @param sqlFile SQL文件<br>
	 * @return SQL语句集合<br>
	 * @throws Exception 异常<br>
	 */
	public static List<String> loadSql(String sqlFile) throws Exception {
		List<String> rtnList = new ArrayList<String>();
		// 声明I/O流
		InputStream input = null;
		try {
			if (FileHelper.isFile(sqlFile)) {
				input = new FileInputStream(FileHelper.getLegalPath(sqlFile));
				// 读取文件内容
				StringBuffer sqlBuf = new StringBuffer();
				byte[] buffer = new byte[1024]; int byteRead = 0;
				while ((byteRead = input.read(buffer)) != -1) {
					sqlBuf.append(new String(buffer, 0, byteRead, "UTF-8"));
				}
				// 替换文本中的换行符
				String[] sqlArray = sqlBuf.toString().split("(;\\s*\\r\\n)|(;\\s*\\n)");
				for (int i = 0; i <sqlArray.length; i ++) {
					String sql = sqlArray[i].replaceAll("--.*", "").trim();
					if (StringHelper.isNotEmpty(sql)) {
						rtnList.add(sql);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(input);
		}
		return rtnList;
	}
}
