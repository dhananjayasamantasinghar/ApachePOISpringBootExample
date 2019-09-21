package com.realspeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.realspeed.extractor.IDataExtractor;

@Service
public class DataManagementService implements IDataManagementService{
	
	@Autowired
	private IDataExtractor extractor;
	
	@Override
	public String getDocument(int age, int amount) {
		return extractor.extractData(age, amount);
	}
}
