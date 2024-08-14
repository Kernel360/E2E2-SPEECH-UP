package com.speech.up.common.exception.custom;

import org.springframework.http.HttpStatus;

import com.speech.up.common.enums.StatusCode;

import lombok.Getter;

@Getter
public abstract class CustomIllegalArgumentException extends IllegalArgumentException {
	private final StatusCode errorCode;

	public CustomIllegalArgumentException(StatusCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public CustomIllegalArgumentException(StatusCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}
}