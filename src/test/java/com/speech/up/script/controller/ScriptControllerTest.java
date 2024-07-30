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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScriptController.class)
public class ScriptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScriptService scriptService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("사용자의 모든 대본 리스트 조회")
    @Test
    public void getUserScripts() throws Exception {
        // Given
        UserEntity user = new UserEntity(1L, "", "", "", "", "", "", "");

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
}