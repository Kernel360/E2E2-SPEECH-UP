package com.speech.up.script.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.script.service.ScriptService;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;

import jakarta.servlet.http.HttpServletRequest;

public class ScriptControllerTest {

	@Mock
	ScriptService scriptService;

	@InjectMocks
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

		//when
		ResponseEntity<List<ScriptGetDto.Response>> actualResponse = scriptController.getScriptAll(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("getScript 테스트")
	@Test
	public void getScriptTest() {
		//given
		Long userId = 1L;
		Long scriptId = 2L;

		//when
		ResponseEntity<ScriptGetDto.Response> actualResponse = scriptController.getScript(userId, scriptId);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("getScriptCount 테스트")
	@Test
	public void getScriptCountTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		ResponseEntity<Long> actualResponse = scriptController.getScriptCount(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("addScript 테스트")
	@Test
	public void addScriptTest() {
		//given
		ScriptAddDto.Request request = mock(ScriptAddDto.Request.class);

		//when
		ResponseEntity<ScriptAddDto.Response> actualResponse = scriptController.addScript(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("updateScript 테스트")
	@Test
	public void updateScriptTest() {
		//given
		ScriptUpdateDto.Request request = mock(ScriptUpdateDto.Request.class);

		//when
		ResponseEntity<ScriptUpdateDto.Response> actualResponse= scriptController.updateScript(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("deleteScript 테스트")
	@Test
	public void deleteScriptTest() {
		//given
		ScriptIsUseDto.Request request = mock(ScriptIsUseDto.Request.class);

		//when
		ResponseEntity<ScriptIsUseDto.Response> actualResponse = scriptController.deleteScript(request);

		//then
		assertNotNull(actualResponse);
	}
}
