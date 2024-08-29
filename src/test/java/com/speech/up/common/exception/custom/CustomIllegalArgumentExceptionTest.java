package com.speech.up.common.exception.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.speech.up.common.enums.StatusCode;

class CustomIllegalArgumentExceptionTest {

	// CustomIllegalArgumentException을 상속한 구체적인 예외 클래스를 정의
	static class TestCustomIllegalArgumentException extends CustomIllegalArgumentException {
		public TestCustomIllegalArgumentException(StatusCode errorCode) {
			super(errorCode);
		}

		public TestCustomIllegalArgumentException(StatusCode errorCode, Throwable cause) {
			super(errorCode, cause);
		}
	}

	@DisplayName("CustomIllegalArgumentException 생성자 테스트 - 메시지 확인")
	@Test
	void testExceptionMessage() {
		CustomIllegalArgumentException exception = new TestCustomIllegalArgumentException(StatusCode.NO_SCRIPTS);

		assertEquals(StatusCode.NO_SCRIPTS, exception.getErrorCode());
		assertEquals("No Scripts", exception.getMessage());
	}

	@DisplayName("CustomIllegalArgumentException 생성자 테스트 - 원인 확인")
	@Test
	void testExceptionWithCause() {
		Throwable cause = new RuntimeException("Cause of the exception");
		CustomIllegalArgumentException exception = new TestCustomIllegalArgumentException(StatusCode.IO_ERROR, cause);

		assertEquals(StatusCode.IO_ERROR, exception.getErrorCode());
		assertEquals("IO Error", exception.getMessage());
		assertEquals(cause, exception.getCause());
	}
}
