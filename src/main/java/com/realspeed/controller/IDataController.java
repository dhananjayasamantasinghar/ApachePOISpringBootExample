package com.realspeed.controller;

import com.realspeed.model.Input;

public interface IDataController {
		public String getDocumentType(int age, int amount);
		public String getDocumentType(Input inputData);
}
