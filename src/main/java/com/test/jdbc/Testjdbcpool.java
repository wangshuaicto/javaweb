package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Testjdbcpool {
	public static void main(String[] args)
	{
//		JdbcUtil.getConnection()
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from account";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				System.out.println("id---"+rs.getInt("id"));
				System.out.println("name"+"---"+rs.getString("name"));
				System.out.println("money---"+rs.getFloat("money"));
				System.out.println("**********************************");
			}
			JdbcUtil.release(conn, pst, rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
