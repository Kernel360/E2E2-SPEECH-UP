package com.speech.up.script.service;

import com.speech.up.common.exception.http.NotFoundException;
import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ScriptService {
	private final ScriptRepository scriptRepository;
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	private List<ScriptEntity> getActiveScriptsByUserId(Long userId) {
		return scriptRepository.findByUserUserIdAndIsUseTrue(userId);
	}

	private List<ScriptGetDto.Response> mapToResponse(List<ScriptEntity> scripts) {
		return scripts.stream()
			.map(ScriptGetDto.Response::toResponse)
			.collect(Collectors.toList());
	}

	public List<ScriptGetDto.Response> getScriptList(HttpServletRequest request) {
		String socialId = jwtProvider.getHeader(request);

		UserEntity userEntity = userRepository.findBySocialId(socialId)
			.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId: " + socialId));

		List<ScriptEntity> activeScripts = getActiveScriptsByUserId(userEntity.getUserId());

		return mapToResponse(activeScripts);
	}

	public ScriptGetDto.Response getScript(Long userId, Long scriptId) {
		ScriptEntity scriptEntity = scriptRepository.findById(scriptId)
			.orElseThrow(() -> new NoSuchElementException("Not found Script with Id: " + scriptId));

		if (!scriptEntity.getUser().getUserId().equals(userId)) {
			throw new NotFoundException("No Script found with Id: " + scriptId);
		}

		return ScriptGetDto.Response.toResponse(scriptEntity);
	}

	public ScriptAddDto.Response addScript(ScriptAddDto.Request scriptAddRequestDto) {
		log.debug("Add Script : {}", scriptAddRequestDto);

		ScriptEntity scriptEntity = ScriptEntity.create(scriptAddRequestDto);

		return ScriptAddDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public ScriptUpdateDto.Response updateScript(ScriptUpdateDto.Request scriptUpdateRequestDto) {
		Optional<UserEntity> userEntity = userRepository.findById(scriptUpdateRequestDto.getUser().getUserId());

		if (userEntity.isEmpty()) {
			throw new NotFoundException("Has No User with Id: " + scriptUpdateRequestDto.getUser().getUserId());
		}

		ScriptEntity scriptEntity = ScriptEntity.update(scriptUpdateRequestDto);

		return ScriptUpdateDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public ScriptIsUseDto.Response deleteScriptById(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		ScriptEntity scriptEntity = ScriptEntity.delete(scriptIsUseRequestDto);

		return ScriptIsUseDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public Long getScriptCount(HttpServletRequest request) {
		String socialId = jwtProvider.getHeader(request);
		UserEntity userEntity = userRepository.findBySocialId(socialId)
			.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId : " + socialId));

		return scriptRepository.countByUserUserIdAndIsUseTrue(userEntity.getUserId());
	}
}
