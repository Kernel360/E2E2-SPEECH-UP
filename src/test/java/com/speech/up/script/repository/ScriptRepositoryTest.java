package com.speech.up.script.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.script.entity.ScriptEntity;

public class ScriptRepositoryTest {
	@Mock
	ScriptRepository scriptRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("유저 아이디로 스크립트 리스트 검색")
	@Test
	public void getScriptListByUserIdTest() {
		//given
		Long userId = 1L;
		List<ScriptEntity> scriptEntity = Collections.singletonList(mock(ScriptEntity.class));

		//when
		when(scriptRepository.findByUserUserIdAndIsUseTrue(userId)).thenReturn(scriptEntity);

		//then
		assertEquals(scriptRepository.findByUserUserIdAndIsUseTrue(userId), scriptEntity);
	}

	@DisplayName("유저 아이디로 사용 중인 스크립트 갯수 검색")
	@Test
	public void getCountScriptListByUserIdTest() {
		//given
		Long userId = 1L;
		Long count = 1L;

		//when
		when(scriptRepository.countByUserUserIdAndIsUseTrue(userId)).thenReturn(1L);

		//then
		assertEquals(scriptRepository.countByUserUserIdAndIsUseTrue(userId), count);
	}
}
