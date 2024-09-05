package com.speech.up.common.exception.custom;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.speech.up.common.enums.StatusCode;

import lombok.Getter;

@Getter
public abstract class CustomIOException extends IOException {
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