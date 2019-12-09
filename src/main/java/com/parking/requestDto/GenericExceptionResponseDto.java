package com.parking.requestDto;

import java.io.Serializable;

public class GenericExceptionResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String info;
	private String message;

	public GenericExceptionResponseDto(String message, int code, String info) {
		super();
		this.code = code;
		this.info = info;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
