package com.speech.up.common.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
	OK(0, "OK"),
	IO_ERROR(1, "IO Error"),
	NO_SCRIPTS(1001, "No Scripts"),
	NO_RECORDS(2002, "No Records"),
	NO_AUTHORIZATION(403, "No Authorization"),
	NO_FILES(2003, "No Files");

	private final int code;
	private final String message;

	StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static StatusCode fromCode(int code) {
		for (StatusCode statusCode : StatusCode.values()) {
			if (statusCode.code == code) {
				return statusCode;
			}
		}
		throw new IllegalArgumentException("Invalid code: " + code);
	}
}
