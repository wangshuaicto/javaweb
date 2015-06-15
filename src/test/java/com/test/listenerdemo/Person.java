package com.test.listenerdemo;

public class Person {

	private PersonListener personlistener;
	
	
	public void eat()
	{
		if(personlistener != null)
		{
			personlistener.doeat(new PersonEvent(this));
		}
	}
	
	public void run()
	{
		if(personlistener!=null)
		{
			personlistener.dorun(new PersonEvent(this));
		}
	}
	
	public void registerPersonListener(PersonListener personlistener)
	{
		this.personlistener = personlistener;
	}
}
