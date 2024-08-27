package com.speech.up.script.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;

import jakarta.persistence.EntityListeners;
import jakarta.servlet.http.HttpServletRequest;

@EntityListeners(AuditingEntityListener.class)
public class ScriptServiceTest {

	@Mock
	private ScriptService scriptService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("대본을 조회하는 기능을 테스트")
	@Test
	public void getScriptListTest() {
		// Given
		HttpServletRequest hasAuthorizationRequest = mock(HttpServletRequest.class);
		HttpServletRequest noHasAuthorizationRequest = mock(HttpServletRequest.class);
		hasAuthorizationRequest.setAttribute("Authorization", "Bearer token");
		List<ScriptGetDto.Response> responses = Collections.singletonList(mock(ScriptGetDto.Response.class));

		// When
		when(scriptService.getScriptList(hasAuthorizationRequest)).thenReturn(responses);
		when(scriptService.getScriptList(noHasAuthorizationRequest)).thenReturn(null);

		// Then
		assertEquals(responses, scriptService.getScriptList(hasAuthorizationRequest));
		assertNotEquals(responses, scriptService.getScriptList(noHasAuthorizationRequest));

	}

	@DisplayName("스크립트 단건 조회")
	@Test
	public void getScript() {
		// Given
		Long scriptId = 1L;
		Long userId = 1L;
		ScriptGetDto.Response response = mock(ScriptGetDto.Response.class);

		// When
		when(scriptService.getScript(userId, scriptId)).thenReturn(response);

		// Then
		assertEquals(response, scriptService.getScript(userId, scriptId));
	}

	@DisplayName("대본 수정 기능 테스트")
	@Test
	public void updateScriptTest() {
		// Given
		ScriptUpdateDto.Request request = mock(ScriptUpdateDto.Request.class);
		ScriptUpdateDto.Response response = mock(ScriptUpdateDto.Response.class);

		// When
		when(scriptService.updateScript(request)).thenReturn(response);

		// Then
		assertEquals(response, scriptService.updateScript(request));
	}

	@DisplayName("대본을 생성하는 기능 테스트")
	@Test
	public void addScriptTest() {
		// Given
		ScriptAddDto.Request request = mock(ScriptAddDto.Request.class);
		ScriptAddDto.Response response = mock(ScriptAddDto.Response.class);

		// When
		when(scriptService.addScript(request)).thenReturn(response);

		// Then
		assertEquals(response, scriptService.addScript(request));
	}

	@DisplayName("대본을 삭제하는 기능 테스트")
	@Test
	public void deleteScriptByIdTest() {
		// Given
		ScriptIsUseDto.Request request = mock(ScriptIsUseDto.Request.class);
		ScriptIsUseDto.Response response = mock(ScriptIsUseDto.Response.class);

		// When
		when(scriptService.deleteScriptById(request)).thenReturn(response);

		// Then
		assertEquals(response, scriptService.deleteScriptById(request));
	}

}
