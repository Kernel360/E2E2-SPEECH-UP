package com.speech.up.common.enums;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusCodeTest {

	@DisplayName("StatusCode enum 값 테스트")
	@Test
	void testStatusCodeValues() {
		assertEquals(0, StatusCode.OK.getCode());
		assertEquals("OK", StatusCode.OK.getMessage());

		assertEquals(1, StatusCode.IO_ERROR.getCode());
		assertEquals("IO Error", StatusCode.IO_ERROR.getMessage());

		assertEquals(1001, StatusCode.NO_SCRIPTS.getCode());
		assertEquals("No Scripts", StatusCode.NO_SCRIPTS.getMessage());

		assertEquals(2002, StatusCode.NO_RECORDS.getCode());
		assertEquals("No Records", StatusCode.NO_RECORDS.getMessage());

		assertEquals(2003, StatusCode.NO_FILES.getCode());
		assertEquals("No Files", StatusCode.NO_FILES.getMessage());
	}

	@DisplayName("fromCode 메서드가 올바른 StatusCode를 반환하는지 테스트")
	@Test
	void testFromCode() {
		assertEquals(StatusCode.OK, StatusCode.fromCode(0));
		assertEquals(StatusCode.IO_ERROR, StatusCode.fromCode(1));
		assertEquals(StatusCode.NO_SCRIPTS, StatusCode.fromCode(1001));
		assertEquals(StatusCode.NO_RECORDS, StatusCode.fromCode(2002));
		assertEquals(StatusCode.NO_FILES, StatusCode.fromCode(2003));
	}

	@DisplayName("fromCode 메서드에서 잘못된 코드 입력 시 예외 발생 테스트")
	@Test
	void testFromCodeInvalid() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			StatusCode.fromCode(9999); // 존재하지 않는 코드
		});

		assertEquals("Invalid code: 9999", exception.getMessage());
	}
}
