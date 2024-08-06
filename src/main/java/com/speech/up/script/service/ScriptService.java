package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScriptService {
	private final ScriptRepository scriptRepository;
	private final UserRepository userRepository;

	private List<ScriptEntity> getActiveScriptsByUserId(Long userId) {
		return scriptRepository.findByUserUserIdAndIsUseTrue(userId);
	}

	private List<ScriptGetDto.Response> mapToResponse(List<ScriptEntity> scripts) {
		return scripts.stream()
			.map(ScriptGetDto.Response::getScripts)
			.collect(Collectors.toList());
	}

	public List<ScriptGetDto.Response> getScriptList(Long userId) {
		List<ScriptEntity> activeScripts = getActiveScriptsByUserId(userId);
		return mapToResponse(activeScripts);
	}

	public ScriptAddDto.Response addScript(ScriptAddDto.Request scriptAddRequestDto) {
		ScriptEntity scriptEntity = ScriptEntity.create(scriptAddRequestDto);
		return ScriptAddDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public ScriptUpdateDto.Response updateScript(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		Optional<UserEntity> userEntity = userRepository.findById(scriptUpdateRequestDto.getUser().getUserId());
		if (userEntity.isEmpty()) {
			throw new EntityNotFoundException("User not found");
		}

		ScriptEntity scriptEntity = ScriptEntity.update(scriptUpdateRequestDto);

		return ScriptUpdateDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public ScriptIsUseDto.Response deleteScriptById(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		ScriptEntity scriptEntity = ScriptEntity.delete(scriptIsUseRequestDto);
		return ScriptIsUseDto.toResponse(scriptRepository.save(scriptEntity));
	}
}
