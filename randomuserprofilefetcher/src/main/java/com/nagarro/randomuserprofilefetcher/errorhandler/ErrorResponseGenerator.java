package com.nagarro.randomuserprofilefetcher.errorhandler;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponseGenerator {

	private String message;
	private int code;
	private HttpStatus status;
	private String timestamp;

	
	public ErrorResponseGenerator(String message, int code, String timestamp, HttpStatus status) {
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	
	
}
