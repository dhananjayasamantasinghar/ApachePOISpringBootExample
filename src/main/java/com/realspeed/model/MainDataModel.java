package com.realspeed.model;

public class MainDataModel {
	private int age;
	private int amount;
	private int document;
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
	public int getDocument() {
		return document;
	}
	public void setDocument(int document) {
		this.document = document;
	}
	@Override
	public String toString() {
		return "MainDataModel [age=" + age + ", amount=" + amount + ", document=" + document + "]";
	}
	
}
