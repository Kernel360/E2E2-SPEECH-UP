package com.speech.up.script.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.mock.web.MockHttpServletRequest;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.persistence.EntityListeners;

@EntityListeners(AuditingEntityListener.class)
class ScriptServiceTest {

	@Mock
	ScriptRepository scriptRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	JwtProvider jwtProvider;
	@Mock
	UserEntity mockUserEntity;
	@Mock
	ScriptEntity mockScriptEntity;

	@InjectMocks
	ScriptService scriptService;

	String socialId;
	Long userId;
	LocalDateTime modifiedAt;
	Long scriptId;
	String title;
	String content;
	boolean isUse;
	ScriptEntity expectedEntity;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		socialId = "mockSocialId";
		userId = 1L;
		modifiedAt = LocalDateTime.now();
		scriptId = 1L;
		title = "title";
		content = "content";
		isUse = false;

		mockUserEntity = mock(UserEntity.class);
		mockScriptEntity = mock(ScriptEntity.class);
		when(mockUserEntity.getUserId()).thenReturn(userId);
		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.of(mockUserEntity));
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUserEntity));

		expectedEntity = new ScriptEntity(content, mockUserEntity, title, false);
		when(scriptRepository.save(any(ScriptEntity.class))).thenReturn(expectedEntity);
	}

	@DisplayName("대본을 조회하는 기능을 테스트")
	@Test
	void getScriptListTest() {
		// Given
		MockHttpServletRequest request = new MockHttpServletRequest();
		List<ScriptEntity> mockScripts = Collections.singletonList(mockScriptEntity);

		// When
		when(jwtProvider.getHeader(request)).thenReturn(socialId);
		when(scriptRepository.findByUserUserIdAndIsUseTrue(userId)).thenReturn(mockScripts);

		List<ScriptGetDto.Response> result = scriptService.getScriptList(request);

		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@DisplayName("스크립트 단건 조회")
	@Test
	void getScript() {
		// Given
		ScriptGetDto.Response expectedResponse = ScriptGetDto.Response.toResponse(mockScriptEntity);

		when(scriptRepository.findById(scriptId)).thenReturn(Optional.of(mockScriptEntity));
		when(mockScriptEntity.getUser()).thenReturn(mockUserEntity);
		when(mockScriptEntity.getModifiedAt()).thenReturn(modifiedAt);

		// When
		ScriptGetDto.Response actualResponse = scriptService.getScript(userId, scriptId);

		// Then
		assertNotNull(actualResponse);
		assertEquals(expectedResponse.getScriptId(), actualResponse.getScriptId());
	}

	@DisplayName("대본 수정 기능 테스트")
	@Test
	void updateScriptTest() {
		// Given
		ScriptUpdateDto.Request request = mock(ScriptUpdateDto.Request.class);

		when(request.getTitle()).thenReturn(title);
		when(request.getContent()).thenReturn(content);
		when(request.getUser()).thenReturn(mockUserEntity);
		when(request.getScriptId()).thenReturn(1L);

		//when
		ScriptUpdateDto.Response actualResponse = scriptService.updateScript(request);
		ScriptUpdateDto.Response response = ScriptUpdateDto.toResponse(expectedEntity);

		// Then
		assertEquals(response.getTitle(), actualResponse.getTitle());
		assertEquals(response.getContent(), actualResponse.getContent());
	}

	@DisplayName("대본을 생성하는 기능 테스트")
	@Test
	void addScriptTest() {
		// Given
		ScriptAddDto.Request request = mock(ScriptAddDto.Request.class);

		when(request.getTitle()).thenReturn(title);
		when(request.getContent()).thenReturn(content);
		when(request.getUser()).thenReturn(mockUserEntity);

		// When
		ScriptAddDto.Response actualResponse = scriptService.addScript(request);

		// Then
		assertNotNull(actualResponse);
		assertEquals(title, actualResponse.getTitle());
		assertEquals(content, actualResponse.getContent());
	}

	@DisplayName("대본을 삭제하는 기능 테스트")
	@Test
	void deleteScriptByIdTest() {
		// Given
		ScriptIsUseDto.Request request = mock(ScriptIsUseDto.Request.class);

		when(request.getTitle()).thenReturn(title);
		when(request.getContent()).thenReturn(content);
		when(request.getUser()).thenReturn(mockUserEntity);
		when(request.getScriptId()).thenReturn(1L);

		//when
		ScriptIsUseDto.Response actualResponse = scriptService.deleteScriptById(request);
		ScriptIsUseDto.Response response = ScriptIsUseDto.toResponse(expectedEntity);

		// Then
		assertEquals(response.getTitle(), actualResponse.getTitle());
		assertFalse(response.isUse());
		assertFalse(actualResponse.isUse());
	}

}
