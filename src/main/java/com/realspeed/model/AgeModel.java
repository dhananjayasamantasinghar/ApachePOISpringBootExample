package com.realspeed.model;

public class AgeModel {
	private int age_id;
	private String age_Range;
	
	public AgeModel() {
	}

	public int getAge_id() {
		return age_id;
	}

	public void setAge_id(int age_id) {
		this.age_id = age_id;
	}

	public String getAge_Range() {
		return age_Range;
	}

	public void setAge_Range(String age_Range) {
		this.age_Range = age_Range;
	}

	@Override
	public String toString() {
		return "AgeModel [age_id=" + age_id + ", age_Range=" + age_Range + "]";
	}
	

	
}
