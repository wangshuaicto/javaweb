package com.test.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCTesClob {
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
//			Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			
			
			//4.向数据库发sql,并获取代表结果集的resultset
			
//			String sql = "UPDATE users set name='wangwu1' where id=3;";
//			statement.executeUpdate(sql);
			
			//---------------------插入--------------------------//
			String sql = "insert into testclob (resume) values (?)";
			
			PreparedStatement st = conn.prepareStatement(sql); //预编译
			
			File file = new File("D:\\a.txt");
			
			System.out.println(file.exists());
			
			BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));	
			
			
			
			st.setCharacterStream(1, br, file.length());
			
			int num = st.executeUpdate();
			
			if(num>0)
			{
				System.out.println(num);
				System.out.println("插入成功");
			}
			//---------------------插入--------------------------//
			//--------------------查询clob
			
			String sqlquery = "select resume from testclob where id=5";
			
			PreparedStatement prs = conn.prepareStatement(sqlquery);
			
			ResultSet rs = prs.executeQuery();
			
			String content = "";
			while(rs.next())
			{
				Reader reader = rs.getCharacterStream("resume");
				char[] buffer = new char[1024*8];
				StringBuffer sb = new StringBuffer();
				int len=0;
				while((len=reader.read(buffer))!=-1)
				{
					sb.append(buffer, 0, len);
				}
				System.out.println(new String(sb.toString().getBytes("utf-8")));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
