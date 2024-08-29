package com.speech.up.common.exception.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class InternalServerErrorExceptionTest {

	@DisplayName("InternalServerErrorException 생성자 테스트")
	@Test
	void testInternalServerErrorException() {
		String errorMessage = "An unexpected error occurred";
		InternalServerErrorException exception = new InternalServerErrorException(errorMessage);

		// 예외의 HTTP 상태 코드와 메시지가 예상대로 설정되었는지 확인
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
		assertEquals(errorMessage, exception.getMessage());
	}

}
