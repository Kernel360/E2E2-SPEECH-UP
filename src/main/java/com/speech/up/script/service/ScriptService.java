package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;
    private final UserRepository userRepository;

    public ResponseEntity<List<ScriptEntity>> getScriptList(Long userId) {
        List<ScriptEntity> scripts = scriptRepository.findByUser_UserId(userId);
        if (scripts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(scripts, HttpStatus.OK);
    }

    public ResponseEntity<ScriptEntity> addScript(ScriptEntity scriptEntity) {
        ScriptEntity script = ScriptEntity.builder()
                .content(scriptEntity.getContent())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .modifiedAt(Timestamp.valueOf(LocalDateTime.now()))
                .user(scriptEntity.getUser())
                .build();

        ScriptEntity resScriptEntity = scriptRepository.save(script);
        return new ResponseEntity<>(resScriptEntity, HttpStatus.OK);
    }

    public ResponseEntity<ScriptEntity> updateScript(ScriptEntity scriptEntity) {
        if (scriptEntity.getUser().getUserId() != null) {
            UserEntity userEntity = userRepository.findById(scriptEntity.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("user not found"));
            scriptEntity.setUser(userEntity);
        }

        ScriptEntity script = scriptRepository.findById(scriptEntity.getScriptId())
                .orElseThrow(() -> new RuntimeException("script not found"));

        script.setContent(scriptEntity.getContent());
        script.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));

        ScriptEntity resScriptEntity = scriptRepository.save(script);
        return new ResponseEntity<>(resScriptEntity, HttpStatus.OK);

    }

    public void deleteScriptById(Long scriptId) {
        scriptRepository.deleteById(scriptId);
    }
}
