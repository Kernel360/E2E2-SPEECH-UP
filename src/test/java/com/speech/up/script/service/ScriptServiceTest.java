package com.speech.up.script.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

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

	@InjectMocks
	private static ScriptService scriptService;


	private static ScriptEntity scriptEntity;
	private static UserEntity userEntity;

	private static ScriptAddDto.Request scriptAddRequestDto;
	private static ScriptAddDto.Response scriptAddResponseDto;

	private static ScriptUpdateDto.Request scriptUpdateRequestDto;
	private static ScriptUpdateDto.Response scriptUpdateDtoResponse;


	private static ScriptIsUseDto.Request scriptIsUseRequestDto;

	private static ScriptIsUseDto.Response scriptIsUseResponseDto;

	private static ScriptGetDto.Response scriptGetDtoResponse;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		userEntity = new UserEntity(
			1L,
			"name",
			"social",
			"email",
			"rank",
			"auth"
		);

		scriptEntity = new ScriptEntity(
			"content",userEntity,false
		);


		scriptUpdateRequestDto = new ScriptUpdateDto.Request(
			1L,
			"content",
			userEntity,
			true
		);

		scriptAddRequestDto = new ScriptAddDto.Request(
			"content",
			userEntity
		);

		scriptIsUseRequestDto = new ScriptIsUseDto.Request(
			1L,
			true,
			"content",
			userEntity
		);

		// scriptEntity = new ScriptEntity(
		// 	"content",
		// 	userEntity,
		// 	true
		// );

	}

	// @DisplayName("getActiveScriptsByUserIdTest")
	// @Test
	// void getActiveScriptsByUserIdTest() {
	// 	Long userId = 1L;
	// 	ScriptEntity scriptEntity = ScriptEntity.create(scriptAddRequestDto); // 필요한 필드 초기화
	// 	when(scriptRepository.findByUserUserIdAndIsUseTrue(userId)).thenReturn(Collections.singletonList(scriptEntity));
	//
	// 	List<ScriptGetDto.Response> result = scriptService.getScriptList(userId);
	//
	// 	assertNotNull(result);
	// 	assertEquals(1, result.size());
	// 	verify(scriptRepository).findByUserUserIdAndIsUseTrue(userId);
	// }

	@DisplayName("대본을 조회하는 기능을 테스트")
	@Test
	public void getScriptListTest() {
	    // Given
	    Long id = 1L;
		Long userId = 1L;
		ScriptEntity scriptEntity = new ScriptEntity("test", userEntity,  "", true);
		List<ScriptEntity> activeScripts = Collections.singletonList(scriptEntity);
		given(scriptRepository.findByUserUserIdAndIsUseTrue(userId)).willReturn(activeScripts);

		// When
	    List<ScriptGetDto.Response> response = scriptService.getScriptList(id);

		// Then
	    // assertThat(response.get(0).getScriptId()).isEqualTo(id);
	    assertThat(response.get(0).getScriptId()).isEqualTo(1L);
	}

	@DisplayName("대본 수정 기능 테스트")
	@Test
	public void updateScriptTest(){
		//given
		given(userRepository.findById(anyLong())).willReturn(Optional.of(userEntity));
		given(scriptRepository.save(any(ScriptEntity.class))).willReturn(scriptEntity);

		//when
		scriptUpdateDtoResponse = scriptService.updateScript(scriptUpdateRequestDto);

		//then
		assertThat(scriptUpdateDtoResponse.getContent()).isEqualTo("content");
	}

	@DisplayName("대본을 생성하는 기능 테스트")
	@Test
	public void addScriptTest() {
	    // Given
		given(scriptRepository.save(any(ScriptEntity.class))).willReturn((scriptEntity));
		// When
		scriptAddResponseDto = scriptService.addScript(scriptAddRequestDto);

		// Then
	    assertThat(scriptAddResponseDto).isNotNull();
	    assertThat(scriptAddResponseDto.getContent()).isEqualTo("content");
	}



	@DisplayName("대본을 삭제하는 기능 테스트")
	@Test
	public void deleteScriptByIdTest() {
	    // Given
		given(scriptRepository.save(any(ScriptEntity.class))).willReturn((scriptEntity));
		// When
		scriptIsUseResponseDto = scriptService.deleteScriptById(scriptIsUseRequestDto);

		// Then
		assertThat(scriptIsUseResponseDto).isNotNull();
		assertThat(scriptIsUseResponseDto.isUse()).isFalse();
		verify(scriptRepository, times(1)).save(any(ScriptEntity.class));

	}

}
