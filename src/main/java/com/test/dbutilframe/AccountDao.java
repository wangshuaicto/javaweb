package com.test.dbutilframe;

import java.sql.SQLException;

public class AccountDao {
	
	public void add(AccountBean account) throws SQLException
	{
		String sql =  "insert into account (name,money) values (?,?)";
		Object[] params = {account.getName(),account.getMoney()};
		JdbcUtils.update(sql, params);
	}
	
	public AccountBean find(int id) throws SQLException
	{
		String sql = "select * from account where id=?";
		Object[] params = {id};
		return (AccountBean)JdbcUtils.Query(sql, params, new BeanHandler(AccountBean.class));
	}
}
