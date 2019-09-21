package com.realspeed.extractor;
/**
 * 
 * @author Dhananjay Samanta
 *
 */
public interface IDataExtractor {
	/**
	 * This method has been written for takes the input as age,amount and return document as per excel Data 
	 * @param age
	 * @param amount
	 * @return
	 */
	String extractData(int age,int amount);
}
