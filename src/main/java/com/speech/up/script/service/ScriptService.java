package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;

    // 유저의 모든 script 리스트로 반환
    public ResponseEntity<List<ScriptEntity>> getScriptList(Long userId) {
        List<ScriptEntity> scripts = scriptRepository.findByUser_UserId(userId);
        if (scripts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(scripts, HttpStatus.OK);
    }

    // 유저의 script 목록에 script 추가
    public ResponseEntity<ScriptEntity> addScript(ScriptEntity scriptEntity) {
        ScriptEntity resScriptEntity = scriptRepository.save(scriptEntity);

        if(resScriptEntity != null){
            return new ResponseEntity<>(resScriptEntity, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(resScriptEntity, HttpStatus.BAD_REQUEST);
        }
    }

    // 유저가 작성한 script 수정
    public ResponseEntity<ScriptEntity> updateScript( ScriptEntity scriptEntity ) {

        ScriptEntity resScriptEntity = scriptRepository.save(scriptEntity);
        return new ResponseEntity<>(resScriptEntity, HttpStatus.OK);

    } // content 수정 시 기존 content 로드, 생성일자 고정, 수정일자 상시 변경

    // 유저가 작성한 script 삭제
    public void deleteScriptById(Long scriptId) {
        scriptRepository.deleteById(scriptId);

    }
}
