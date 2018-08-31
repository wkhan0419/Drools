package com.beans;

public class Person 
{
private String name;
private int  age;
private double salary;
private String citizenship;


public String getCitizenship()
{
	return citizenship;
}
public void setCitizenship(String citizenship) 
{
	this.citizenship = citizenship;
}
public String getName() 
{
	return name;
}
public void setName(String name) 
{
	this.name = name;
}
public int getAge() 
{
	return age;
}
public void setAge(int age) 
{
	this.age = age;
}
public double getSalary() 
{
	return salary;
}
public void setSalary(double salary) 
{
	this.salary = salary;
}

@Override
public String toString() 
{
	return "Person [name=" + name + ", age=" + age + ", salary=" + salary + ", citizenship=" + citizenship + ", ]";
}


}
