package com.speech.up.common.exception.http;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends HttpBaseException {
	public UnAuthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}
}