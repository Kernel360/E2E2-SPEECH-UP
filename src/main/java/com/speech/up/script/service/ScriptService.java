package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.internal.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;
    private final UserRepository userRepository;

    public List<ScriptGetDto.ScriptGetResponseDto> getScriptList(Long userId) {

        return scriptRepository.findByUserUserId(userId).stream()
                .map(ScriptGetDto.ScriptGetResponseDto::getScripts).collect(Collectors.toList());
    }

    public ScriptAddDto.ScriptAddResponseDto addScript(ScriptAddDto.ScriptAddRequestDto scriptAddRequestDto) {
        ScriptEntity scriptEntity = new ScriptEntity(scriptAddRequestDto);
        return ScriptAddDto.ScriptAddResponseDto.addScript(scriptRepository.save(scriptEntity));
    }

    public ScriptUpdateDto.ScriptUpdateResponseDto updateScript(ScriptUpdateDto.ScriptUpdateRequestDto scriptUpdateRequestDto) {
        ScriptEntity scriptEntity = new ScriptEntity(scriptUpdateRequestDto);
        return ScriptUpdateDto.ScriptUpdateResponseDto.updateScript(scriptRepository.save(scriptEntity));
    }

    public void deleteScriptById(Long scriptId) {
        scriptRepository.deleteById(scriptId);
    }
}
