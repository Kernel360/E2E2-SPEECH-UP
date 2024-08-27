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
public class ScriptServiceTest {

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
	private ScriptService scriptService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("대본을 조회하는 기능을 테스트")
	@Test
	public void getScriptListTest() {
		// Given
		String socialId = "mockSocialId";
		Long userId = 1L;
		MockHttpServletRequest request = new MockHttpServletRequest();
		List<ScriptEntity> mockScripts = Collections.singletonList(mockScriptEntity);

		// When
		when(jwtProvider.getHeader(request)).thenReturn(socialId);
		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.of(mockUserEntity));
		when(mockUserEntity.getUserId()).thenReturn(userId);
		when(scriptRepository.findByUserUserIdAndIsUseTrue(userId)).thenReturn(mockScripts);

		List<ScriptGetDto.Response> result = scriptService.getScriptList(request);

		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@DisplayName("스크립트 단건 조회")
	@Test
	public void getScript() {
		// Given
		Long userId = 1L;
		Long scriptId = 1L;
		String socialId = "mockSocialId";
		LocalDateTime modifiedAt = LocalDateTime.now();

		UserEntity mockUserEntity = mock(UserEntity.class);
		ScriptEntity mockScriptEntity = mock(ScriptEntity.class);
		ScriptGetDto.Response expectedResponse = ScriptGetDto.Response.toResponse(mockScriptEntity);

		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.of(mockUserEntity));
		when(mockUserEntity.getUserId()).thenReturn(userId);
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
	public void updateScriptTest() {
		// Given
		Long userId = 1L;
		Long scriptId = 1L;
		String content = "Content";
		String title = "Title";
		boolean isUse = true;


		ScriptUpdateDto.Request scriptUpdateRequestDto = mock(ScriptUpdateDto.Request.class);
		when(scriptUpdateRequestDto.getScriptId()).thenReturn(scriptId);
		when(scriptUpdateRequestDto.getContent()).thenReturn(content);
		when(scriptUpdateRequestDto.getTitle()).thenReturn(title);
		when(mockUserEntity.getUserId()).thenReturn(userId);
		when(scriptUpdateRequestDto.getUser()).thenReturn(mockUserEntity);
		when(scriptUpdateRequestDto.isUse()).thenReturn(isUse);
		// When
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUserEntity));
		ScriptEntity scriptEntity = ScriptEntity.update(scriptUpdateRequestDto);

		ScriptUpdateDto.Response response = ScriptUpdateDto.toResponse(scriptEntity);

		// Then
		 assertEquals(response.getTitle(), "Title");

	}

	@DisplayName("대본을 생성하는 기능 테스트")
	@Test
	public void addScriptTest() {
		// Given
		ScriptAddDto.Request scriptAddRequestDto = mock(ScriptAddDto.Request.class);
		when(scriptAddRequestDto.getContent()).thenReturn("content");
		when(scriptAddRequestDto.getUser()).thenReturn(mockUserEntity);
		when(scriptAddRequestDto.getTitle()).thenReturn("title");

		// When
		ScriptEntity scriptEntity = ScriptEntity.create(scriptAddRequestDto);
		ScriptAddDto.Response response = ScriptAddDto.toResponse(scriptEntity);

		// Then
		assertEquals(response.getTitle(), "title");
	}

	@DisplayName("대본을 삭제하는 기능 테스트")
	@Test
	public void deleteScriptByIdTest() {
		// Given
		ScriptIsUseDto.Request scriptIsUseDtoRequest = mock(ScriptIsUseDto.Request.class);
		when(scriptIsUseDtoRequest.getScriptId()).thenReturn(1L);
		when(scriptIsUseDtoRequest.isUse()).thenReturn(true);
		when(scriptIsUseDtoRequest.getContent()).thenReturn("content");
		when(scriptIsUseDtoRequest.getUser()).thenReturn(mockUserEntity);
		when(scriptIsUseDtoRequest.getTitle()).thenReturn("title");

		// When
		ScriptEntity scriptEntity = ScriptEntity.delete(scriptIsUseDtoRequest);
		ScriptIsUseDto.Response response = ScriptIsUseDto.toResponse(scriptEntity);

		// Then
		assertFalse(response.isUse());
	}

}
