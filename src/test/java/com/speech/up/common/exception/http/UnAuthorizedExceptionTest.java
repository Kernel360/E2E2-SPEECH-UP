package com.speech.up.common.exception.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnAuthorizedExceptionTest {

	@DisplayName("UnAuthorizedException 생성자 테스트")
	@Test
	void testUnAuthorizedException() {
		String errorMessage = "Unauthorized access";
		UnAuthorizedException exception = new UnAuthorizedException(errorMessage);

		// 예외의 HTTP 상태 코드와 메시지가 예상대로 설정되었는지 확인
		assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
		assertEquals(errorMessage, exception.getMessage());
	}

	@DisplayName("UnAuthorizedException을 통한 예외 발생 테스트")
	@Test
	void testThrowUnAuthorizedException() {
		String errorMessage = "Unauthorized access";

		UnAuthorizedException thrown = assertThrows(UnAuthorizedException.class, () -> {
			throw new UnAuthorizedException(errorMessage);
		});

		// 예외가 발생했을 때의 상태 코드와 메시지가 예상대로인지 확인
		assertEquals(HttpStatus.UNAUTHORIZED, thrown.getStatusCode());
		assertEquals(errorMessage, thrown.getMessage());
	}
}
