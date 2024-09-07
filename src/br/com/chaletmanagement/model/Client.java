package br.com.chaletmanagement.model;

import java.time.LocalDate;

public class Client
{
	private String name;
	private String id;
	private LocalDate birthday;
	
	public Client()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public LocalDate getBirthday()
	{
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday)
	{
		this.birthday = birthday;
	}
}
