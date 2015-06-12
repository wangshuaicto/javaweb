package com.test.jdbc;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JdbcPool implements DataSource{
	
	private static  LinkedList<Connection> listConnections = new LinkedList<Connection>();
	
	static{
		InputStream in = JdbcPool.class.getClassLoader().getResourceAsStream("db1.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			String driver = prop.getProperty("driver");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			String initSize = prop.getProperty("jdbcpoolinitSize");
			int jdbcsize = Integer.parseInt(initSize);
			Class.forName(driver);
			
			for(int i=0;i<jdbcsize;i++)
			{
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("初始化了链接" + conn);
				listConnections.add(conn);
				System.out.println("将" + conn+"添加到连接池");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if(listConnections.size()>0)
		{
			System.out.println("listConnections数据库连接池大小是" + listConnections.size());
			final Connection conn = listConnections.removeFirst();
			/**
			 * 此处特别注意   listConnections里面获取到的全是JDBC4Connection非接口
			 * Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(),这种方式会报ClassCastException
			 * */
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
				
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					// TODO Auto-generated method stub
					if(!method.getName().equals("close"))
					{
						return method.invoke(conn, args);
					}else
					{
						//如果调用的是Connection代理对象的close方法，则将链接归还给连接池
						listConnections.add(conn);
						System.out.println(conn + "被还给listConnections数据库连接池了！！");
						System.out.println("listConnections数据库连接池大小为" + listConnections.size());
						return null;
					}
				}
			});
		}else
		{
			throw new RuntimeException("对不起，数据库繁忙");
		}
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
