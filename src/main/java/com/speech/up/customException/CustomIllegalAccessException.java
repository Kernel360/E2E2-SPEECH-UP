package com.speech.up.customException;

public class CustomIllegalAccessException extends IllegalArgumentException{
	public CustomIllegalAccessException(String message) {
		super(message);
	}
}
