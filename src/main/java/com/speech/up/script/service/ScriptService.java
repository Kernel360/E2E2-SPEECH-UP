package com.speech.up.script.service;

import com.speech.up.common.exception.http.NotFoundException;
import com.speech.up.oAuth.provider.JwtProvider;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional // => 예상하지 못한 상황에서 오류가 발생하여 하여 데이터의 부정합이 발생하는 경우, 다시 원상복귀 해야 하는 상황에 대비 하기 위해
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
		String authorization = request.getHeader("Authorization");
		if(authorization != null && authorization.startsWith("Bearer ")) {
			authorization = authorization.substring(7);
		}
		String socialId = jwtProvider.validate(authorization);
		UserEntity userEntity = userRepository.findBySocialId(socialId);

		List<ScriptEntity> activeScripts = getActiveScriptsByUserId(userEntity.getUserId());
		return mapToResponse(activeScripts);
	}

	public ScriptGetDto.Response getScript(Long userId, Long scriptId) {
		if (!scriptRepository.findById(scriptId).get().getUser().getUserId().equals(userId)) {
			throw new NotFoundException("Has No Script with Id: " + scriptId);
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
			throw new NotFoundException("Has No User with Id: " + scriptUpdateRequestDto.getUser().getUserId());
		}

		ScriptEntity scriptEntity = ScriptEntity.update(scriptUpdateRequestDto);
		return ScriptUpdateDto.toResponse(scriptRepository.save(scriptEntity));
	}

	public ScriptIsUseDto.Response deleteScriptById(ScriptIsUseDto.Request scriptIsUseRequestDto) {
		ScriptEntity scriptEntity = ScriptEntity.delete(scriptIsUseRequestDto);
		return ScriptIsUseDto.toResponse(scriptRepository.save(scriptEntity));
	}
}
