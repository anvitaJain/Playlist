package com.pllaylistExtractor.utils;

public class InvalidInputException extends Exception {

	/**
	 * This is to handle checked exception if input is not valid. 
	 * added default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String payload;
	
	public InvalidInputException(String message) {
		super(message);
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	
}
