package com.tav.coupons.enums;



public enum ErrorType {
	INVALID_USER(400, "Invalid user error"), 
	ILLEGAL_USER_INPUT(400, "Illegal user input"),
	USER_ERROR(400, "User error"),
	WORNG_INPUT(400, "Wrong input"), 
	DB_ERROR(500, "Database error"), 
	DATA_NOT_FOUND(500, "Data not found"), 
	OUT_OF_STOCK_OR_EXPIRED(304, "Out of stock or expired"), 
	START_DATE_BIGGER_THAN_END_DATE(400, "Start date bigger than end date"), 
	CANNOT_PARSE_DATE(304,"Cannot parse date"),
	HACKING_ATTEMPT(800, "Hacking attempt detected");

	private final int internalErrorCode;
	private final String internalMessage;


	//private Constructor 

	private ErrorType(int internalErrorCode, String internalMessage){
		this.internalErrorCode = internalErrorCode;
		this.internalMessage = internalMessage;

	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getInternalMessage() {
		return internalMessage;
	}

}
