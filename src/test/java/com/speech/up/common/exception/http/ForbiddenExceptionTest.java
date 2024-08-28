package com.speech.up.common.exception.http;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class ForbiddenExceptionTest {

	@DisplayName("ForbiddenException 생성자 테스트")
	@Test
	void testForbiddenException() {
		String errorMessage = "Access is denied";
		ForbiddenException exception = new ForbiddenException(errorMessage);

		// 예외의 HTTP 상태 코드와 메시지가 예상대로 설정되었는지 확인
		assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
		assertEquals(errorMessage, exception.getMessage());
	}
}
