package com.test.listenerdemo;

public class PersonTest {
	public static void main(String[] args)
	{
		Person p = new Person();
		p.registerPersonListener(new PersonListener() {
			
			public void dorun(PersonEvent e) {
				// TODO Auto-generated method stub
				System.out.println("run。。。");
			}
			
			public void doeat(PersonEvent e) {
				// TODO Auto-generated method stub
				System.out.println("eat。。。");
			}
		});
		
		p.eat();
		
		p.run();
	}
}
