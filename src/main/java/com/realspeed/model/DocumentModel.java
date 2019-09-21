package com.realspeed.model;

public class DocumentModel {
	private int id;
	private String document;
	
	public DocumentModel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "DocumentModel [id=" + id + ", document=" + document + "]";
	}
	
	
	
}
