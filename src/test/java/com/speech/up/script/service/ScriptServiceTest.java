package com.speech.up.script.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.mock.web.MockHttpServletRequest;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.persistence.EntityListeners;

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

	UserEntity userEntity;
	ScriptEntity scriptEntity;
	RecordEntity recordEntity;

	String socialId = "socialId";
	String bearer = "Bearer ";
	String token = bearer + socialId;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		userEntity = Mockito.mock(UserEntity.class);
		given(userEntity.getUserId()).willReturn(1L);
		given(userEntity.getName()).willReturn("name");
		given(userEntity.getSocialId()).willReturn("socialId");
		given(userEntity.getEmail()).willReturn("email");
		given(userEntity.getLevel()).willReturn("level");
		given(userEntity.getAuthorization()).willReturn(token);

		recordEntity = mock(RecordEntity.class);
		given(recordEntity.getRecordId()).willReturn(1L);

		List<RecordEntity> recordEntities = List.of(recordEntity);

		scriptEntity = mock(ScriptEntity.class);
		given(scriptEntity.getScriptId()).willReturn(1L);
		given(scriptEntity.getTitle()).willReturn("title");
		given(scriptEntity.getContent()).willReturn("content");
		given(scriptEntity.getUser()).willReturn(userEntity);
		given(scriptEntity.getRecordId()).willReturn(recordEntities);
	}

	@DisplayName("대본을 조회하는 기능을 테스트")
	@Test
	public void getScriptListTest() {
		// Given

		MockHttpServletRequest httpRequest = new MockHttpServletRequest();
		httpRequest.addHeader("Authorization", token);

		List<ScriptEntity> scriptEntities = Collections.singletonList(scriptEntity);

		given(jwtProvider.validate(socialId)).willReturn(socialId);
		given(userRepository.findBySocialId(socialId)).willReturn(userEntity);
		given(scriptRepository.findByUserUserIdAndIsUseTrue(anyLong())).willReturn(scriptEntities);

		// When
		List<ScriptGetDto.Response> response = scriptService.getScriptList(httpRequest);

		// Then
		assertThat(response).hasSize(1);
		verify(jwtProvider, times(1)).validate(socialId);
		verify(userRepository, times(1)).findBySocialId(socialId);
		verify(scriptRepository, times(1)).findByUserUserIdAndIsUseTrue(anyLong());
	}

	@DisplayName("스크립트 단건 조회")
	@Test
	public void getScript() {
		// 	given
		Long userId = 1L;
		Long scriptId = 1L;

		given(scriptRepository.findById(scriptId)).willReturn(Optional.of(scriptEntity));

		ScriptGetDto.Response scriptGetResponse;

		// 	when

		scriptGetResponse = scriptService.getScript(userId, scriptId);

		// 	then

		verify(scriptRepository, times(2)).findById(scriptId);
		assertThat(scriptGetResponse).isNotNull();
		assert (scriptGetResponse.getScriptId()).equals(scriptEntity.getScriptId());
	}

	@DisplayName("대본 수정 기능 테스트")
	@Test
	public void updateScriptTest() {
		//given

		ScriptUpdateDto.Request scriptUpdateRequestDto = Mockito.mock(ScriptUpdateDto.Request.class);
		given(scriptUpdateRequestDto.getScriptId()).willReturn(1L);
		given(scriptUpdateRequestDto.getTitle()).willReturn("title");
		given(scriptUpdateRequestDto.getContent()).willReturn("content");
		given(scriptUpdateRequestDto.getUser()).willReturn(userEntity);

		ScriptUpdateDto.Response scriptUpdateResponse;

		given(userRepository.findById(anyLong())).willReturn(Optional.of(userEntity));
		given(scriptRepository.save(any(ScriptEntity.class))).willReturn(scriptEntity);

		//when
		scriptUpdateResponse = scriptService.updateScript(scriptUpdateRequestDto);

		//then
		assertThat(scriptUpdateResponse.getContent()).isEqualTo("content");
		verify(userRepository, times(1)).findById(anyLong());
		verify(scriptRepository, times(1)).save(any(ScriptEntity.class));

	}

	@DisplayName("대본을 생성하는 기능 테스트")
	@Test
	public void addScriptTest() {
		// Given

		ScriptAddDto.Request scriptAddRequest = Mockito.mock(ScriptAddDto.Request.class);
		given(scriptAddRequest.getTitle()).willReturn("title");
		given(scriptAddRequest.getContent()).willReturn("content");
		given(scriptAddRequest.getUser()).willReturn(userEntity);
		ScriptAddDto.Response response;

		given(scriptRepository.save(any(ScriptEntity.class))).willReturn(scriptEntity);
		// When
		response = scriptService.addScript(scriptAddRequest);

		// Then
		assertThat(response).isNotNull();
		assertThat(response.getContent()).isEqualTo("content");
		assertThat(response.getTitle()).isEqualTo("title");

	}

	@DisplayName("대본을 삭제하는 기능 테스트")
	@Test
	public void deleteScriptByIdTest() {
		// Given

		ScriptIsUseDto.Request scriptIsUseRequestDto = Mockito.mock(ScriptIsUseDto.Request.class);
		given(scriptIsUseRequestDto.getScriptId()).willReturn(1L);
		given(scriptIsUseRequestDto.getTitle()).willReturn("title");
		given(scriptIsUseRequestDto.getContent()).willReturn("content");
		given(scriptIsUseRequestDto.getUser()).willReturn(userEntity);
		given(scriptIsUseRequestDto.isUse()).willReturn(true);

		ScriptIsUseDto.Response scriptIsUseResponse;

		given(scriptRepository.save(any(ScriptEntity.class))).willReturn(scriptEntity);

		// When
		scriptIsUseResponse = scriptService.deleteScriptById(scriptIsUseRequestDto);

		// Then
		assertThat(scriptIsUseResponse).isNotNull();
		assertThat(scriptIsUseResponse.isUse()).isFalse();
		verify(scriptRepository, times(1)).save(any(ScriptEntity.class));

	}

}
