package com.speech.up.script.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.service.ScriptService;
import com.speech.up.user.entity.UserEntity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScriptController.class)
public class ScriptControllerTest {
	private final String testBody = "{\n"
		+ "    \"script_id\" : 6,\n"
		+ "    \"content\" : \" dfsddsfdsfa\",\n"
		+ "    \"created_at\" :\"2024-07-30T09:36:26\",\n"
		+ "    \"modified_at\":\"2024-07-30T09:36:26\",\n"
		+ "    \"user\" :{\n"
		+ "        \"userId\" : 1,\n"
		+ "        \"name\" : \"ss\",\n"
		+ "        \"socialId\" : \"fds\",\n"
		+ "        \"password\" : \"121\",\n"
		+ "        \"token\" : \"fds\",\n"
		+ "        \"address\" : \"fdsfs\",\n"
		+ "        \"rank\" : \"fds\",\n"
		+ "        \"authorization\": \"dfsaf\"\n"
		+ "    }\n"
		+ "}";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ScriptService scriptService;

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private DefaultMockMvcBuilder mockMvcBuilder;

	@DisplayName("사용자의 모든 대본 리스트 조회")
	@Test
	public void getUserScripts() throws Exception {
		// Given
		UserEntity user = new UserEntity(1L, "ad", "asd", "asd", "sada", "dad", "1", "qew");

		ScriptEntity scriptEntity = new ScriptEntity(
			1L,
			"이것은 테스트 대본입니다.",
			Timestamp.valueOf(LocalDateTime.now()),
			Timestamp.valueOf(LocalDateTime.now()),
			user
		);
		List<ScriptEntity> scriptEntities = Collections.singletonList(scriptEntity);
		when(scriptService.getScriptList(1L)).thenReturn(ResponseEntity.ok(scriptEntities));

		// When & Then
		mockMvc.perform(get("/speech-script/{userId}", 1L)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].script_id").value(1L))
			.andExpect(jsonPath("$[0].content").value("이것은 테스트 대본입니다."));
	}

	@DisplayName("스크립트 생성 요청 테스트")
	@Test
	public void addScriptTest() throws Exception {

		// Then
		mockMvc.perform(post("/speech-script")
				.contentType(MediaType.APPLICATION_JSON)
				.content(testBody)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

	}

	@DisplayName("스크립트 수정 요청 테스트")
	@Test
	public void updateScriptTest() throws Exception {

		mockMvc.perform(put("/speech-script")
				.contentType(MediaType.APPLICATION_JSON)
				.content(testBody)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

	}

	@DisplayName("스크립트 삭제 요청 테스트")
	@Test
	public void deleteScriptTest() throws Exception {

		// Then
		mockMvc.perform(delete("/speech-script/{userId}", 1L)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

}



