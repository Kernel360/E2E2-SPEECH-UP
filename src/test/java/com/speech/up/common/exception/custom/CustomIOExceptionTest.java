package com.speech.up.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import com.speech.up.common.enums.StatusCode;

class CustomIOExceptionTest {

	// CustomIOException을 상속한 구체적인 예외 클래스를 정의
	static class TestCustomIOException extends CustomIOException {
		public TestCustomIOException(StatusCode errorCode) {
			super(errorCode);
		}

		public TestCustomIOException(StatusCode errorCode, Throwable cause) {
			super(errorCode, cause);
		}
	}

	@DisplayName("CustomIOException 생성자 테스트 - 메시지 확인")
	@Test
	void testExceptionMessage() {
		CustomIOException exception = new TestCustomIOException(StatusCode.NO_FILES);

		assertEquals(StatusCode.NO_FILES, exception.getErrorCode());
		assertEquals("No Files", exception.getMessage());
	}

	@DisplayName("CustomIOException 생성자 테스트 - 원인 확인")
	@Test
	void testExceptionWithCause() {
		Throwable cause = new IOException("Cause of the exception");
		CustomIOException exception = new TestCustomIOException(StatusCode.IO_ERROR, cause);

		assertEquals(StatusCode.IO_ERROR, exception.getErrorCode());
		assertEquals("IO Error", exception.getMessage());
		assertEquals(cause, exception.getCause());
	}
}
