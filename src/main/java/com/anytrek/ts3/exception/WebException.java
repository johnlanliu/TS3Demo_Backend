package com.anytrek.ts3.exception;

/**
 * 
 * @author kkzhu
 */
public class WebException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public String errorCode;
	public String errorMsg;
	public WebException(String errorCode){
		super();
		this.errorCode = errorCode;
	}
	
	public WebException(String errorCode, String errorMsg){
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getMessage(){
		return this.errorMsg+",errorCode:"+this.errorCode;
	}
}
