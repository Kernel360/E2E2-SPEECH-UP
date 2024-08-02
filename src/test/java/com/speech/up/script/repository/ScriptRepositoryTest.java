package com.speech.up.script.repository;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;

@SpringBootTest
@ActiveProfiles("test")
public class ScriptRepositoryTest {

	/*@Autowired
	private ScriptRepository scriptRepository;

	private UserEntity userEntity;

	@BeforeEach
	public void setUp() {
		userEntity = UserEntity.builder()
			.address("123 Test St")
			.rank("A")
			.token("token")
			.authorization("auth")
			.socialId("socialId")
			.userId(1L)
			.name("Test User")
			.password("password")
			.build();
	}

	@Test
	public void testSaveScript() {
		// Given
		ScriptEntity scriptEntity = ScriptEntity.builder()
			.content("Test content")
			.scriptId(7L)// 필드 값 설정
			.user(userEntity)
			.build();

		// When
		ScriptEntity savedScript = scriptRepository.save(scriptEntity);

		// Then
		then(savedScript).isNotNull();
		then(savedScript.getScriptId()).isNotNull(); // ID가 생성되었는지 확인
		then(savedScript.getContent()).isEqualTo("Test content");
	}

	// 삭제
	@Test
	public void testDeleteScript() {
		// Given
		ScriptEntity scriptEntity = ScriptEntity.builder()
			.content("deleted content")
			.scriptId(1L)
			.user(userEntity)
			.build();
		ScriptEntity savedScript = scriptRepository.save(scriptEntity);

		// When
		scriptRepository.deleteById(savedScript.getScriptId());
		List<ScriptEntity> scripts = scriptRepository.findByUser_UserId(userEntity.getUserId());

		// Then
		for (ScriptEntity script : scripts) {
			then(script.getContent()).isNotEqualTo("deleted content");
		}
	}

	// 조회
	@Test
	public void testFindScript() {
		// Given
		Long userId = 1L;

		// When
		List<ScriptEntity> savedScript = scriptRepository.findByUser_UserId(userId);

		// Then
		then(savedScript).isNotNull();
		for (ScriptEntity script : savedScript) {
			then(script.getUser().getUserId()).isEqualTo(userId);
		}
	}*/
}
