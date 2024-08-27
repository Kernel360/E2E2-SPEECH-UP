package com.speech.up.record.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.record.entity.RecordEntity;

public class RecordRepositoryTest {

	@Mock
	RecordRepository recordRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("스크립트 아이디로 사용 중인 녹음데이터 리스트 가져오기 함수 호출")
	@Test
	public void findByScriptIdUseTest() {
		//given
		List<RecordEntity> recordEntity = Collections.singletonList(mock(RecordEntity.class));
		Long scriptId = 1L;

		//when
		when(recordRepository.findByScriptScriptIdAndIsUseTrue(scriptId)).thenReturn(recordEntity);

		//then
		assertEquals(recordEntity, recordRepository.findByScriptScriptIdAndIsUseTrue(scriptId));
	}
}
