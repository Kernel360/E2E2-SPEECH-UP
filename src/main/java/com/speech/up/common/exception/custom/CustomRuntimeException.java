package com.speech.up.common.exception.custom;

import org.springframework.http.HttpStatus;

import com.speech.up.common.enums.StatusCode;

import lombok.Getter;

@Getter
public abstract class CustomRuntimeException extends RuntimeException {
	private final HttpStatus statusCode;
	private final StatusCode errorCode;

	public CustomRuntimeException(HttpStatus statusCode, StatusCode errorCode) {
		super(errorCode.getMessage());
		this.statusCode = statusCode;
		this.errorCode = errorCode;
	}

	public CustomRuntimeException(HttpStatus statusCode, StatusCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.statusCode = statusCode;
		this.errorCode = errorCode;
	}
}