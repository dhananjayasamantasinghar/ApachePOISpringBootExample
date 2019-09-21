package com.realspeed.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Input {
	private int age;
	private int amount;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
