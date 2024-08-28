package com.speech.up.common.exception.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

	@DisplayName("NotFoundException 생성자 테스트")
	@Test
	void testNotFoundException() {
		String errorMessage = "Resource not found";
		NotFoundException exception = new NotFoundException(errorMessage);

		// 예외의 HTTP 상태 코드와 메시지가 예상대로 설정되었는지 확인
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals(errorMessage, exception.getMessage());
	}
}
