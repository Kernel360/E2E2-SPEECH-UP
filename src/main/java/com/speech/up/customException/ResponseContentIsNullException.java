package com.speech.up.customException;

public class ResponseContentIsNullException extends RuntimeException {
	final String message;

	public ResponseContentIsNullException(final String message) {
		this.message = message;
	}
}
