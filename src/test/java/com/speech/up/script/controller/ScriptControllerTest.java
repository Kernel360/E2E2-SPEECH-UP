package com.speech.up.script.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;

import jakarta.servlet.http.HttpServletRequest;

public class ScriptControllerTest {
	@Mock
	ScriptController scriptController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("getScriptAll 테스트")
	@Test
	public void getScriptAllTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);
		List<ScriptGetDto.Response> response = Collections.singletonList(mock(ScriptGetDto.Response.class));

		//when
		when(scriptController.getScriptAll(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(scriptController.getScriptAll(request), ResponseEntity.ok(response));
	}

	@DisplayName("getScript 테스트")
	@Test
	public void getScriptTest() {
		//given
		Long userId = 1L;
		Long scriptId = 2L;
		ScriptGetDto.Response response = mock(ScriptGetDto.Response.class);

		//when
		when(scriptController.getScript(userId, scriptId)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(scriptController.getScript(userId, scriptId), ResponseEntity.ok(response));
	}

	@DisplayName("getScriptCount 테스트")
	@Test
	public void getScriptCountTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);
		ResponseEntity<Long> response = ResponseEntity.ok(1L);

		//when
		when(scriptController.getScriptCount(request)).thenReturn(ResponseEntity.ok(2L));

		//then
		assertEquals(scriptController.getScriptCount(request), ResponseEntity.ok(2L));
	}

	@DisplayName("addScript 테스트")
	@Test
	public void addScriptTest() {
		//given
		ScriptAddDto.Request request = mock(ScriptAddDto.Request.class);
		ScriptAddDto.Response response = mock(ScriptAddDto.Response.class);

		//when
		when(scriptController.addScript(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(scriptController.addScript(request), ResponseEntity.ok(response));
	}

	@DisplayName("updateScript 테스트")
	@Test
	public void updateScriptTest() {
		//given
		ScriptUpdateDto.Request request = mock(ScriptUpdateDto.Request.class);
		ScriptUpdateDto.Response response = mock(ScriptUpdateDto.Response.class);

		//when
		when(scriptController.updateScript(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(scriptController.updateScript(request), ResponseEntity.ok(response));
	}

	@DisplayName("deleteScript 테스트")
	@Test
	public void deleteScriptTest() {
		//given
		ScriptIsUseDto.Request request = mock(ScriptIsUseDto.Request.class);
		ScriptIsUseDto.Response response = mock(ScriptIsUseDto.Response.class);

		//when
		when(scriptController.deleteScript(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(scriptController.deleteScript(request), ResponseEntity.ok(response));
	}
}
