package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ScriptServiceTest {

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private ScriptService scriptService;

    public ScriptServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("대본을 조회하는 기능 // 성공! ")
    @Test
    public void getAllScripts() {
        // Given
        Long id = 1L;
        ScriptEntity scriptEntity = new ScriptEntity();
        List<ScriptEntity> scriptEntities = Collections.singletonList(scriptEntity);
        when(scriptRepository.findByUser_UserId(id)).thenReturn(scriptEntities);
        // When
        ResponseEntity<List<ScriptEntity>> response = scriptService.getScriptList(id);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(scriptEntities);
    }
}
