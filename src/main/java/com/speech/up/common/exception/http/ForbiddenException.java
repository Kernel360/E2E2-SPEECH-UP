package com.speech.up.common.exception.http;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpBaseException {
	public ForbiddenException(String message) {
		super(HttpStatus.FORBIDDEN, message);
	}
}