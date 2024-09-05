package com.speech.up.record.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.record.repository.RecordRepository;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;
import com.speech.up.user.entity.UserEntity;

public class RecordServiceTest {

	@Mock
	RecordRepository recordRepository;
	@Mock
	ScriptRepository scriptRepository;
	@Mock
	ScriptEntity scriptEntity;
	@Mock
	UserEntity userEntity;
	@Mock
	RecordEntity recordEntity;

	@InjectMocks
	RecordService recordService;

	Long scriptId;
	Long userId;
	String socialId;
	String content;
	String title;
	boolean isUse;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

	Long recordId;
	byte[] audio;
	String languageCode;
	boolean isAnalyzed;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		userId = 1L;
		scriptId = 1L;
		socialId = "socialId";
		content = "content";
		title = "title";
		isUse = true;
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
		recordId = 1L;
		audio = new byte[] {1, 2, 3};
		languageCode = "en";
		isAnalyzed = false;

		userEntity = mock(UserEntity.class);
		scriptEntity = mock(ScriptEntity.class);
		recordEntity = mock(RecordEntity.class);

		when(userEntity.getUserId()).thenReturn(userId);
		when(userEntity.getSocialId()).thenReturn(socialId);
		when(scriptEntity.getScriptId()).thenReturn(scriptId);
		when(scriptEntity.getTitle()).thenReturn(title);
		when(scriptEntity.getContent()).thenReturn(content);
		when(scriptEntity.getCreatedAt()).thenReturn(LocalDateTime.now());
		when(scriptEntity.getUser()).thenReturn(userEntity);
		when(recordEntity.getRecordId()).thenReturn(recordId);
		when(recordEntity.getCreatedAt()).thenReturn(LocalDateTime.now());
		when(recordEntity.getAudio()).thenReturn(audio);
		when(recordEntity.getLanguageCode()).thenReturn(languageCode);

	}

	@DisplayName("스크립트 아이디로 녹음 데이터 리스트 가져오기")
	@Test
	public void getRecordListByScriptIdTest() {
		//given
		List<RecordGetDto.Response> response = Collections.singletonList(mock(RecordGetDto.Response.class));
		List<RecordEntity> expected = Collections.singletonList(mock(RecordEntity.class));
		when(recordRepository.findByScriptScriptIdAndIsUseTrue(scriptId)).thenReturn(expected);

		//when
		List<RecordGetDto.Response> actualResponse = recordService.getRecordList(scriptId);

		//then
		assertEquals(response.size(), actualResponse.size());
	}

	@DisplayName("녹음 데이터 추가하기")
	@Test
	public void addRecordTest() throws IOException {
		// Given
		MultipartFile file = mock(MultipartFile.class);
		InputStream inputStream = mock(InputStream.class);
		when(file.getInputStream()).thenReturn(inputStream);
		String languageCode = "korean";
		Long scriptId = 1L;
		ScriptEntity scriptEntity = mock(ScriptEntity.class);

		when(scriptRepository.findById(scriptId)).thenReturn(Optional.of(scriptEntity));
		when(recordRepository.save(any(RecordEntity.class))).thenReturn(recordEntity);

			// When
		try {
			RecordAddDto.Response actualResponse = recordService.addRecord(file, languageCode, scriptId);
			assertNotNull(actualResponse);
		} catch (UnsupportedAudioFileException e) {
			// Then
			assertEquals("File of unsupported format", e.getMessage());
		}
	}

	@DisplayName("녹음 데이터 삭제하기")
	@Test
	public void deleteRecordTest() {
		// given
		RecordIsUseDto.Request request = mock(RecordIsUseDto.Request.class);
		when(request.getRecordEntity()).thenReturn(recordEntity);
		when(recordEntity.getRecordId()).thenReturn(recordId);

		// when
		RecordEntity recordResult = RecordEntity.delete(request);
		when(recordRepository.save(any(RecordEntity.class))).thenReturn(recordEntity);
		RecordIsUseDto.Response actualResponse = recordService.deleteRecord(request);

		// then
		assertEquals(recordResult.getRecordId(), actualResponse.getRecordId());
	}

	@DisplayName("녹음 아이디로 분석 메서드 호출하기")
	@Test
	public void callAnalyzeMethodByRecordIdTest() {
		//given
		when(recordRepository.findById(recordId)).thenReturn(Optional.of(recordEntity));
		RecordEntity recordResult = RecordEntity.analyze(recordEntity);

		//when
		when(recordRepository.save(any(RecordEntity.class))).thenReturn(recordEntity);
		recordService.analyzed(recordId);

		//then
		assertEquals(recordResult.getRecordId(), recordId);
		verify(recordRepository, times(1)).save(any(RecordEntity.class));
	}
}
