package com.speech.up.script.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ScriptController.class)
public class ScriptControllerTest {
	@Autowired
	private MockMvc mockMvc;


	private String body = "{\n"
		+ "    \"content\" : \" dfsddsfdsfa\",\n"
		+ "    \"user\" :{\n"
		+ "        \"user_id\" : 1,\n"
		+ "        \"name\" : \"ss\",\n"
		+ "        \"social_id\" : \"fds\",\n"
		+ "        \"password\" : \"121\",\n"
		+ "        \"token\" : \"fds\",\n"
		+ "        \"address\" : \"fdsfs\",\n"
		+ "        \"rank\" : \"fds\",\n"
		+ "        \"authorization\": \"dfsaf\"\n"
		+ "    }\n"
		+ "}";

	@DisplayName("Script GetMapping 테스트")
	@Test
	public void getScriptAllTest() throws Exception {
		//given
		Long scriptId = 7L;
		//when & then
		mockMvc.perform(get("/speech-script/{scriptId}",scriptId)).andExpect(status().isOk());
	}

	@DisplayName("대본 생성 PostMapping 테스트")
	@Test
	public void addScriptTest() throws Exception {

		//when&then
		mockMvc.perform(post("/speech-script")
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@DisplayName("대본 수정 PutMapping 테스트")
	@Test
	public void updateScriptTest() throws Exception {

		mockMvc.perform(put("/speech-script")
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@DisplayName("대본 삭제 PatchMapping 테스트")
	@Test
	public void deleteScriptTest() throws Exception {

		mockMvc.perform(patch("/speech-script")
				.content(body)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}


}



