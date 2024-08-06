package com.speech.up.script.repository;

import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
@ActiveProfiles("test")
public class ScriptRepositoryTest {

	@Autowired
	private ScriptRepository scriptRepository;

	private ScriptEntity scriptEntity;
	private static UserEntity userEntity;

	@BeforeAll
	public static void setup() {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("api.voice.url", dotenv.get("API_VOICE_TO_TEXT_URL"));
		System.setProperty("api.voice.accessKey", dotenv.get("API_VOICE_TO_TEXT_ACCESS_KEY"));
		System.setProperty("api.voice.language-code", dotenv.get("API_VOICE_TO_LANGUAGE_CODE"));

		System.setProperty("speech-flow.api.key.id", dotenv.get("SPEECH_FLOW_API_KEY_ID"));
		System.setProperty("speech-flow.api.key.secret", dotenv.get("SPEECH_FLOW_API_KEY_SECRET"));
		System.setProperty("speech-flow.lang", dotenv.get("SPEECH_FLOW_LANG"));
		System.setProperty("speech-flow.result.type", dotenv.get("SPEECH_FLOW_RESULT_TYPE"));
		userEntity = new UserEntity(1001L, "test", "zxcv@zxcv.com", "pw", "token", "address", "bronze",
			"authorization");

	}

	@Test
	@DisplayName("")
	public void testSaveScript() {
		// Given
		String content = "Test Content";
		ScriptAddDto.Request scriptAddRequestDto = new ScriptAddDto.Request(content, userEntity);
		scriptEntity = ScriptEntity.create(scriptAddRequestDto);

		// When
		ScriptEntity savedScript = scriptRepository.save(scriptEntity);

		// Then
		then(savedScript).isNotNull();
		then(savedScript.getScriptId()).isNotNull(); // ID가 생성되었는지 확인
		then(savedScript.getContent()).isEqualTo("Test Content");
	}

	// 삭제
	@Test
	@DisplayName("수정 및 삭제")
	public void testDeleteScript() {
		// Given
		long scriptId = 178L;
		String content = "Test Content";
		LocalDateTime date = LocalDateTime.of(2024, 7, 31, 9, 54, 0);
		ScriptUpdateDto.Request request =
			new ScriptUpdateDto.Request(scriptId, content, date, userEntity);
		ScriptEntity scriptEntity1 = ScriptEntity.update(request);
		ScriptEntity savedScriptOrigin = scriptRepository.save(scriptEntity1);

		ScriptIsUseDto.Request scriptIsUseRequestDto =
			new ScriptIsUseDto.Request(savedScriptOrigin.getScriptId(), false, savedScriptOrigin.getContent(), date,
				userEntity);
		ScriptEntity scriptEntity = ScriptEntity.delete(scriptIsUseRequestDto);

		// When
		ScriptEntity savedScript = scriptRepository.save(scriptEntity);

		// Then
		then(savedScriptOrigin.isUse()).isNotEqualTo(savedScript.isUse());
		scriptRepository.deleteById(scriptEntity.getScriptId());
	}

	// 조회
	@Test
	public void testFindScript() {
		// Given
		Long userId = 1L;

		// When
		List<ScriptEntity> savedScript = scriptRepository.findByUserUserIdAndIsUseTrue(userId);

		// Then
		then(savedScript).isNotNull();
		for (ScriptEntity script : savedScript) {
			then(script.getUser().getUserId()).isEqualTo(userId);
		}
	}

	@AfterEach
	public void tearDown() {
		if (scriptEntity == null)
			return;
		if (scriptEntity.getScriptId() == null)
			return;
		scriptRepository.deleteById(scriptEntity.getScriptId());
	}
}
