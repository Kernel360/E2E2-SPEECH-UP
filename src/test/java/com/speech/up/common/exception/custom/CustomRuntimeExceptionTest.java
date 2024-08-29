package com.speech.up.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import com.speech.up.common.enums.StatusCode;

class CustomRuntimeExceptionTest {

	// CustomRuntimeException을 상속한 구체적인 예외 클래스를 정의
	static class TestCustomRuntimeException extends CustomRuntimeException {
		public TestCustomRuntimeException(HttpStatus statusCode, StatusCode errorCode) {
			super(statusCode, errorCode);
		}

		public TestCustomRuntimeException(HttpStatus statusCode, StatusCode errorCode, Throwable cause) {
			super(statusCode, errorCode, cause);
		}
	}

	@DisplayName("CustomRuntimeException 생성자 테스트 - 상태 코드와 메시지 확인")
	@Test
	void testExceptionWithStatusCodeAndMessage() {
		CustomRuntimeException exception = new TestCustomRuntimeException(HttpStatus.BAD_REQUEST, StatusCode.NO_SCRIPTS);

		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
		assertEquals(StatusCode.NO_SCRIPTS, exception.getErrorCode());
		assertEquals("No Scripts", exception.getMessage());
	}

	@DisplayName("CustomRuntimeException 생성자 테스트 - 원인 확인")
	@Test
	void testExceptionWithCause() {
		Throwable cause = new RuntimeException("Cause of the exception");
		CustomRuntimeException exception = new TestCustomRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.IO_ERROR, cause);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
		assertEquals(StatusCode.IO_ERROR, exception.getErrorCode());
		assertEquals("IO Error", exception.getMessage());
		assertEquals(cause, exception.getCause());
	}
}
