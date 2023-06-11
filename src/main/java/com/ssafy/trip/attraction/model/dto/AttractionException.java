package com.ssafy.trip.attraction.model.dto;

public class AttractionException extends RuntimeException{
	public AttractionException() {}
	public AttractionException(String msg) {
		super(msg);
	}
}
