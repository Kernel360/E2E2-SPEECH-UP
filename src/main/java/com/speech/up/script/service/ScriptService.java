package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final ScriptRepository scriptRepository;

    public List<ScriptGetDto.ScriptGetResponseDto> getScriptList(Long userId) {

        return scriptRepository.findByUserUserIdAndIsUseTrue(userId)
                .stream()
                .map(ScriptGetDto.ScriptGetResponseDto::getScripts)
                .collect(Collectors.toList());
    }

    public ScriptAddDto.ScriptAddResponseDto addScript(ScriptAddDto.ScriptAddRequestDto scriptAddRequestDto) {
        ScriptEntity scriptEntity = new ScriptEntity(scriptAddRequestDto);
        return ScriptAddDto.ScriptAddResponseDto.addScript(scriptRepository.save(scriptEntity));
    }

    public ScriptUpdateDto.ScriptUpdateResponseDto updateScript(ScriptUpdateDto.ScriptUpdateRequestDto scriptUpdateRequestDto) {
        ScriptEntity scriptEntity = new ScriptEntity(scriptUpdateRequestDto);
        return ScriptUpdateDto.ScriptUpdateResponseDto.updateScript(scriptRepository.save(scriptEntity));
    }

    public ScriptIsUseDto.ScriptIsUseResponseDto deleteScriptById(ScriptIsUseDto.ScriptIsUseRequestDto scriptIsUseRequestDto) {
        ScriptEntity scriptEntity = new ScriptEntity(scriptIsUseRequestDto);
        return ScriptIsUseDto.ScriptIsUseResponseDto.deleteScript(scriptRepository.save(scriptEntity));
    }
}
