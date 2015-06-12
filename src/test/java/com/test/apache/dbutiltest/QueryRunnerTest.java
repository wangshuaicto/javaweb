package com.test.apache.dbutiltest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.junit.Test;

import com.test.jdbc.JDBCTest;
import com.test.jdbc.JdbcPool;

public class QueryRunnerTest {
	
	/**
	 * 测试表
	 	create table queryrn(
	 		id int primary key auto_increment,
	 		name varchar(40),
	 		password varchar(40),
	 		email varchar(60),
	 		birthday date
	 	)
	 * @throws SQLException 
	 	
	 
	 
	 */
	
	@Test
	public void add() throws SQLException
	{
		QueryRunner qr = new QueryRunner(new JdbcPool());
		String sql = "insert into queryrn (name,password,email,birthday) values (?,?,?,?)";
		Object[] param = {"lucy","541221","wesds@qq.com",new Date()};
//		Object[] param = {"tom","541221","wesds@qq.com",new Date()};
		qr.update(sql, param);
	}
	
	
	@Test
	public void delete() throws SQLException
	{
		QueryRunner qr = new QueryRunner(new JdbcPool());
		String sql = "delete from queryrn where id=?";
		qr.update(sql, 1);
	}
	
	
	@Test
	public void testArrayHandler() throws SQLException
	{
		QueryRunner qr = new QueryRunner(new JdbcPool());
		String sql = "select * from queryrn";
		Object[] result = qr.query(sql, new ArrayHandler());
		for(int i=0;i<result.length;i++)
		{
			System.out.println(result[i].toString());
		}
	}
	
	@Test
	public void testArrayListHandler() throws SQLException
	{
		QueryRunner qr = new QueryRunner(new JdbcPool());
		String sql = "select * from queryrn";
		List<Object[]> resultlist = qr.query(sql, new ArrayListHandler());
		for(int i=0;i<resultlist.size();i++)
		{
			Object[] temp = resultlist.get(i);
			System.out.println("----------------"+(i+1)+"--------------------");
			for(int j=0;j<temp.length; j++)
			{
				System.out.print(temp[j]+" : ");
			}
			System.out.println();
		}
	}

}
