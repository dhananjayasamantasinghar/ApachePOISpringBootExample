package com.realspeed.model;

public class AmountModel {
	private int amount_id;
	private String amountRange;
	
	public AmountModel() {
	}
	
	public int getAmount_id() {
		return amount_id;
	}
	public void setAmount_id(int amount_id) {
		this.amount_id = amount_id;
	}
	public String getAmountRange() {
		return amountRange;
	}
	public void setAmountRange(String amountRange) {
		this.amountRange = amountRange;
	}

	@Override
	public String toString() {
		return "AmountModel [amount_id=" + amount_id + ", amountRange=" + amountRange + "]";
	}
	
	
}
