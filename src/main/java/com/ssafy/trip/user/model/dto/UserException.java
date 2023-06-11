package com.ssafy.trip.user.model.dto;

public class UserException extends RuntimeException{
	public UserException() {}
	public UserException(String msg) {
		super(msg);
	}
}
