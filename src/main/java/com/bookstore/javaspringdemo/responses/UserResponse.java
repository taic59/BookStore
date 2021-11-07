package com.bookstore.javaspringdemo.responses;

public class UserResponse {
    private String status;
	private Object message;
	
	public UserResponse(String status, Object message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
}