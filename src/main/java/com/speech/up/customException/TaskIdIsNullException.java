package com.speech.up.customException;

public class TaskIdIsNullException extends RuntimeException {
	final String message;

	public TaskIdIsNullException(final String message) {
		this.message = message;
	}
}
