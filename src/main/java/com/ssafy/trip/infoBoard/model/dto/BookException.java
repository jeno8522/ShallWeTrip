package com.ssafy.trip.infoBoard.model.dto;

public class BookException extends RuntimeException{
	public BookException() {}
	public BookException(String msg) {
		super(msg);
	}
}
