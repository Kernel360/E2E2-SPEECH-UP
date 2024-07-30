package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.user.entity.UserEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ScriptServiceTest {


    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private ScriptService scriptService;

    private ScriptEntity scriptEntity;

    public ScriptServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUp() {
        UserEntity userEntity = UserEntity.builder()
            .socialId("fds")
            .name("fds")
            .password("fds")
            .rank("fds")
            .authorization("fds")
            .token("fds")
            .userId(1L)
            .build();

        scriptEntity = ScriptEntity.builder()
            .scriptId(1L) // 테스트용 ID 설정
            .content("Test Content")
            .createdAt(Timestamp.valueOf(LocalDateTime.now()))
            .modifiedAt(Timestamp.valueOf(LocalDateTime.now()))
            .user(userEntity)
            .build();
    }

    @DisplayName("대본을 조회하는 기능을 테스트")
    @Test
    public void getAllScripts() {
        // Given
        Long id = 1L;
        List<ScriptEntity> scriptEntities = Collections.singletonList(scriptEntity);
        when(scriptRepository.findByUser_UserId(id)).thenReturn(scriptEntities);
        // When
        ResponseEntity<List<ScriptEntity>> response = scriptService.getScriptList(id);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(scriptEntities);
    }

    @DisplayName("대본을 생성하는 기능 테스트")
    @Test
    public void createScript() {
        // Given
        when(scriptRepository.save(scriptEntity)).thenReturn(scriptEntity);
        // When
        ResponseEntity<ScriptEntity> response = scriptService.addScript(scriptEntity);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isEqualTo("Test Content");
        assertThat(response.getBody().getUser()).isEqualTo(scriptEntity.getUser());
    }

    @DisplayName("대본을 삭제하는 기능 테스트")
    @Test
    public void deleteScript() {
        // Given
        Long scriptId = scriptEntity.getScriptId();
        doNothing().when(scriptRepository).deleteById(scriptId);

        // When
        scriptService.deleteScriptById(scriptId);

        // Then
        verify(scriptRepository, times(1)).deleteById(scriptId);

    }

}
