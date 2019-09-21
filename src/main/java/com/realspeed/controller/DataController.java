package com.realspeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realspeed.model.Input;
import com.realspeed.service.IDataManagementService;


@RestController
@RequestMapping("/rest")
public class DataController implements IDataController{
	
	@Autowired
	private IDataManagementService service;
	
	/**
	 * http://localhost:8085/rest/document/45/25000
	 */
	@Override
	@GetMapping(value="/document/{age}/{amount}",produces={MediaType.TEXT_PLAIN_VALUE})
	public String getDocumentType(@PathVariable("age") int age,@PathVariable("amount") int amount){
		return service.getDocument(age, amount);
	}
	
	/**
	 * http://localhost:8085/rest/document
		<input>
		<age>45</age>
		<amount>25000</amount>
		</input>
	 *
	 */
	@PostMapping(value="/document",produces={MediaType.TEXT_PLAIN_VALUE},consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public String getDocumentType(@RequestBody Input inputData){
		return service.getDocument(inputData.getAge(),inputData.getAmount());
	}
}
