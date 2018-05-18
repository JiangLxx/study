package com.qcgx.frame.utils.dbs;

import java.sql.*;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.StringHelper;

/**
 * <p>基于数据库操作的通用工具类</p>
 * @author FLY @date 2017-06-02<br>
 * @version 1.0<br>
 */
public class DbHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private DbHelper() {}
	
	/**
	 * 事务进行回滚<p>
	 * @param conn 数据库连接对象<br>
	 */
	public static void rollback(Connection conn) {
		try {
			if (conn != null ) { conn.rollback(); }
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>关闭并释放数据库查询结果集占用资源</p>
	 * @param rs 查询结果集<br>
	 */
	public static void cleanUpResultSet(ResultSet rs) {
		try {
			if (CommHelper.isNotNull(rs) && rs.isClosed() == false) {
				rs.close(); rs = null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>关闭并释放数据库声明对象占用资源</p>
	 * @param stmt 声明对象<br>
	 */
	public static void cleanUpStatement(Statement stmt) {
		try {
			if (CommHelper.isNotNull(stmt) && stmt.isClosed() == false) {
				stmt.close(); stmt = null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>关闭并释放数据库连接对象占用资源</p>
	 * @param con 数据库连接对象<br>
	 */
	public static void cleanUpConnection(Connection con) {
		try {
			if (CommHelper.isNotNull(con) && con.isClosed() == false) {
				con.close(); con = null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>关闭并释放数据库操作相关对象占用资源</p>
	 * @param con 数据库连接对象<br>
	 * @param rs 数据查询结果集<br>
	 */
	public static void cleanUp(Connection con, ResultSet rs) {
		DbHelper.cleanUpResultSet(rs); DbHelper.cleanUpConnection(con);
	}
	
	/**
	 * <p>关闭并释放数据库操作相关对象占用资源</p>
	 * @param con 数据库连接对象<br>
	 * @param stmt 数据库声明对象<br>
	 */
	public static void cleanUp(Connection con, Statement stmt) {
		DbHelper.cleanUpStatement(stmt); DbHelper.cleanUpConnection(con);
	}
	
	/**
	 * <p>关闭并释放数据库声明对象占用资源</p>
	 * @param con 数据库连接对象<br>
	 * @param stmt 声明对象<br>
	 */
	public static void CleanUp(Connection con, CallableStatement stmt) {
		DbHelper.cleanUpCallableStatement(stmt); DbHelper.cleanUpConnection(con);
	}
	
	/**
	 * <p>关闭并释放数据库声明对象占用资源</p>
	 * @param stmt 声明对象<br>
	 */
	public static void cleanUpCallableStatement(CallableStatement stmt) {
		try {
			if (CommHelper.isNotNull(stmt) && stmt.isClosed() == false) {
				stmt.close(); stmt = null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>关闭并释放数据库操作对象占用资源</p>
	 * @param con 数据库连接对象<br>
	 * @param stmt 数据库声明对象<br>
	 * @param rs 数据查询结果集<br>
	 */
	public static void cleanUp(Connection con, Statement stmt, ResultSet rs) {
		DbHelper.cleanUpResultSet(rs); DbHelper.cleanUpStatement(stmt); DbHelper.cleanUpConnection(con);
	}
	
	/**
	 * <p>根据数据库相关信息获取数据库连接对象</p>
	 * @param driver 驱动<br>
	 * @param url 路径<br>
	 * @param user 用户名<br>
	 * @param password 密码<br>
	 * @return Connection 数据库连接对象<br>
	 */
	public static Connection getConnection(String driver, String url, String user, String password) {
		Connection con = null;
		// 合法性验证
		if (StringHelper.isNotEmpty(driver) && StringHelper.isNotEmpty(url) && StringHelper.isNotEmpty(user)) {
			try {
				Class.forName(driver); con = DriverManager.getConnection(url, user, StringHelper.getLegalString(password));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return con;
	}
}
