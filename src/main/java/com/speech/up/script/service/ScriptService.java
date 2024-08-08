package com.speech.up.script.service;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional // => 예상하지 못한 상황에서 오류가 발생하여 하여 데이터의 부정합이 발생하는 경우, 다시 원상복귀 해야 하는 상황에 대비 하기 위해
public class ScriptService {
	private final ScriptRepository scriptRepository;
	private final UserRepository userRepository;

	private List<ScriptEntity> getActiveScriptsByUserId(Long userId) {
		return scriptRepository.findByUserUserIdAndIsUseTrue(userId);
	}

	private List<ScriptGetDto.Response> mapToResponse(List<ScriptEntity> scripts) {
		return scripts.stream()
			.map(ScriptGetDto.Response::toResponse)
			.collect(Collectors.toList());
	}

	public List<ScriptGetDto.Response> getScriptList(Long userId) {
		List<ScriptEntity> activeScripts = getActiveScriptsByUserId(userId);
		return mapToResponse(activeScripts);
	}

	public ScriptGetDto.Response getScript(Long userId, Long scriptId) {
		if (!scriptRepository.findById(scriptId).get().getUser().getUserId().equals(userId)) {
			throw new EntityNotFoundException();
		} else {
			ScriptEntity scriptEntity = scriptRepository.findById(scriptId).get();
			return ScriptGetDto.Response.toResponse(scriptEntity);
		}
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
		/*
		ScriptEntity script = scriptRepository.findById(scriptUpdateRequestDto.getScriptId()).get();
		script.update(scriptUpdateRequestDto);
		*/
		ScriptEntity scriptEntity = ScriptEntity.update(scriptUpdateRequestDto);
		return ScriptUpdateDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public ScriptIsUseDto.Response deleteScriptById(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		ScriptEntity scriptEntity = ScriptEntity.delete(scriptIsUseRequestDto);
		return ScriptIsUseDto.toResponse(scriptRepository.save(scriptEntity));
	}
}
