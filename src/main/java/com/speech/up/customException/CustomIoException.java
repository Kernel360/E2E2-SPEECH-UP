package com.speech.up.customException;

import java.io.IOException;

public class CustomIoException extends IOException {
	public CustomIoException(String message) {
		super(message);
	}
}
