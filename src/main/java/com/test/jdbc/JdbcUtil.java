package com.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private JdbcUtil(){}
	
	private static JdbcPool pool = new JdbcPool();
	
	public static Connection getConnection() throws SQLException
	{
		return pool.getConnection();
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs)
	{
		if(rs != null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs =	null;
		}
		if(st != null)
		{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			st = null;
		}
		if(conn != null)
		{
			try {
				conn.close(); //关闭数据库连接对象
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
