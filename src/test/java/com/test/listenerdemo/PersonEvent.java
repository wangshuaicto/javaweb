package com.test.listenerdemo;

public class PersonEvent {
	
	private Person source;
	public PersonEvent()
	{
		
	}
	
	public PersonEvent(Person person)
	{
		this.source = person;
	}
}
