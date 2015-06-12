package com.test.dbutilframe;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static
	{
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db1.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			Class.forName(driver);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static Connection getConnecton() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
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
		}
		if(st != null)
		{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!= null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static Object Query(String sql,Object[] params,ResultSetHandler rsh) throws SQLException
	{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		
		try
		{
			conn = getConnecton();
			st = conn.prepareStatement(sql);
			for(int i = 0;i<params.length;i++)
			{
				st.setObject(i+1, params[i]);
			}
			rs = st.executeQuery();
			return rsh.handler(rs);
		}finally
		{
			release(conn, st, rs);
		}
	}
	
	
	public static int update(String sql,Object[] params) throws SQLException
	{
		Connection conn = null;
		PreparedStatement st = null;
		conn = getConnecton();
		try
		{
			st = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				st.setObject(i+1, params[i]);
			}
			return st.executeUpdate();
		}finally
		{
			release(conn, st, null);
		}
	}
}
