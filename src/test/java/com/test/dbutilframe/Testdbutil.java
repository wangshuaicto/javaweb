package com.test.dbutilframe;

import java.sql.SQLException;

import org.junit.Test;

public class Testdbutil {

	@Test
	public void test() {

		try {

			AccountBean account = new AccountBean();
			account.setName("D");
			account.setMoney(1000.0f);
			new AccountDao().add(account);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testquery()
	{
		try {
			AccountBean account = new AccountDao().find(1);
			System.out.println("name："+account.getName()+"\nMoney："+account.getMoney());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
