package com.speech.up.common.exception.custom;

import com.speech.up.common.enums.StatusCode;

import lombok.Getter;

@Getter
public class CustomIOException extends RuntimeException {
	private final StatusCode errorCode;

	public CustomIOException(StatusCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public CustomIOException(StatusCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}
}