package com.speech.up.script.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.mock.web.MockHttpServletRequest;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.persistence.EntityListeners;
import jakarta.servlet.ServletException;

@EntityListeners(AuditingEntityListener.class)
public class ScriptServiceTest {

	// private Scrip

	@Mock
	private ScriptRepository scriptRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private JwtProvider jwtProvider;

	@InjectMocks
	private ScriptService scriptService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@DisplayName("대본을 조회하는 기능을 테스트")
	@Test
	public void getScriptListTest() throws
		ServletException,
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		// Given
		String token = "Bearer test";
		String socialId = "mockedSocialId";

		MockHttpServletRequest id = new MockHttpServletRequest();
		id.addHeader("Authorization", token);
		id.addHeader("socialId", socialId);

		Constructor<UserEntity> constructor = UserEntity.class.getDeclaredConstructor(Long.class ,String.class, String.class,
			String.class, String.class, String.class);
		constructor.setAccessible(true);
		UserEntity user = constructor.newInstance(1L, "name", "mockedSocialId", "email", "rank", "Bearer test");

		ScriptEntity scriptEntity = new ScriptEntity("test", user, "", true);
		List<ScriptEntity> activeScripts = Collections.singletonList(scriptEntity);

		given(jwtProvider.validate(token.substring(7))).willReturn(user.getSocialId());
		given(jwtProvider.validate(token)).willReturn(socialId);
		given(userRepository.findBySocialId(socialId)).willReturn(user);
		given(scriptRepository.findByUserUserIdAndIsUseTrue(anyLong())).willReturn(activeScripts);

		// When
		List<ScriptGetDto.Response> response = scriptService.getScriptList(id);

		// Then
		assertThat(response).hasSize(1);
		assertThat(response.get(0).getTitle()).isEqualTo("");
	}

	@DisplayName("대본 수정 기능 테스트")
	@Test
	public void updateScriptTest() throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		//given
		Constructor<UserEntity> userEntityConstructor = UserEntity.class.getDeclaredConstructor(
			Long.class, String.class, String.class, String.class, String.class, String.class);
		userEntityConstructor.setAccessible(true);

		UserEntity userEntity = userEntityConstructor.newInstance(1L, "name", "mockedSocialId", "email", "rank", "Bearer test");

		Constructor<ScriptUpdateDto.Request> scriptUpdateRequestConstructor = ScriptUpdateDto.Request.class.getDeclaredConstructor(
			Long.class, String.class, String.class, UserEntity.class, boolean.class);
		scriptUpdateRequestConstructor.setAccessible(true);

		ScriptUpdateDto.Request scriptUpdateRequestDto = scriptUpdateRequestConstructor.newInstance(1L, "content", "title", userEntity,
			true);

		ScriptUpdateDto.Response scriptUpdateResponse;

		given(userRepository.findById(anyLong())).willReturn(Optional.of(userEntity));
		given(scriptRepository.save(any(ScriptEntity.class))).willReturn(ScriptEntity.update(scriptUpdateRequestDto));

		//when
		scriptUpdateResponse = scriptService.updateScript(scriptUpdateRequestDto);

		//then
		assertThat(scriptUpdateResponse.getContent()).isEqualTo("content");
	}

	@DisplayName("대본을 생성하는 기능 테스트")
	@Test
	public void addScriptTest() throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		// Given
		UserEntity mockUser = mock(UserEntity.class);

		Constructor<ScriptAddDto.Request> constructor = ScriptAddDto.Request.class.getDeclaredConstructor(String.class,
			UserEntity.class, String.class);
		constructor.setAccessible(true);

		ScriptAddDto.Request request = constructor.newInstance("test", mockUser, "sample title");

		ScriptAddDto.Response response;

		MockHttpServletRequest id = new MockHttpServletRequest();

		given(scriptRepository.save(any(ScriptEntity.class))).willReturn((ScriptEntity.create(request)));
		// When
		response = scriptService.addScript(request);

		// Then
		assertThat(response).isNotNull();
		assertThat(response.getContent()).isEqualTo("test");
		assertThat(response.getTitle()).isEqualTo("sample title");

	}

	@DisplayName("대본을 삭제하는 기능 테스트")
	@Test
	public void deleteScriptByIdTest() throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		// Given

		Constructor<UserEntity> userEntityConstructor = UserEntity.class.getDeclaredConstructor(
			Long.class, String.class, String.class, String.class, String.class, String.class);
		userEntityConstructor.setAccessible(true);

		UserEntity userEntity = userEntityConstructor.newInstance(1L, "name", "mockedSocialId", "email", "rank",
			"Bearer test");

		Constructor<ScriptIsUseDto.Request> constructor = ScriptIsUseDto.Request.class.getDeclaredConstructor(
			Long.class, boolean.class, String.class, String.class ,UserEntity.class);
		constructor.setAccessible(true);

		ScriptIsUseDto.Request scriptIsUseRequestDto = constructor.newInstance(1L, true , "content" , "title" , userEntity);

		ScriptIsUseDto.Response scriptIsUseResponse;

		given(userRepository.findById(anyLong())).willReturn(Optional.of(userEntity));
		given(scriptRepository.save(any(ScriptEntity.class))).willReturn(ScriptEntity.delete(scriptIsUseRequestDto));

		// When
		scriptIsUseResponse = scriptService.deleteScriptById(scriptIsUseRequestDto);

		// Then
		assertThat(scriptIsUseResponse).isNotNull();
		assertThat(scriptIsUseResponse.isUse()).isFalse();
		verify(scriptRepository, times(1)).save(any(ScriptEntity.class));

	}

}
