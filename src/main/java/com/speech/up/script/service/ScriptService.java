package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;

    public ResponseEntity<List<ScriptEntity>> getScriptList(Long userId) {
        List<ScriptEntity> scripts = scriptRepository.findByUser_UserId(userId);
        if (scripts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(scripts, HttpStatus.OK);
    }

    public ResponseEntity<ScriptEntity> addScript(ScriptEntity scriptEntity) {
        ScriptEntity resScriptEntity = scriptRepository.save(scriptEntity);
        return new ResponseEntity<>(resScriptEntity, HttpStatus.OK);
    }

    public ResponseEntity<ScriptEntity> updateScript( ScriptEntity scriptEntity ) {
        ScriptEntity resScriptEntity = scriptRepository.save(scriptEntity);
        return new ResponseEntity<>(resScriptEntity, HttpStatus.OK);

    }

    public void deleteScriptById(Long scriptId) {
        scriptRepository.deleteById(scriptId);
    }
}
