package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTest {
	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost:3306/sqlsty";
		String username = "root";
		String password = "123456";
		
		
		
		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取与数据库的链接
			Connection conn = DriverManager.getConnection(url, username, password);
			//3.获取向数据库发送sql的Statment
			Statement statement = conn.createStatement();
//			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			
			
			//4.向数据库发sql,并获取代表结果集的resultset
			
//			String sql = "UPDATE users set name='wangwu1' where id=3;";
//			statement.executeUpdate(sql);
			
			
			String sql = "select * from users";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				System.out.println("id :"+rs.getObject("id"));
				System.out.println("name :"+rs.getObject("name"));
				System.out.println("===================");
			}
			
			rs.close();
			statement.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			Properties p = System.getProperties();
			System.out.println(p);
		}
	}
}
