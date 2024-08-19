package com.speech.up.record.service;

import com.speech.up.api.converter.WavToRaw;
import com.speech.up.log.CustomLogger;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.record.repository.RecordRepository;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;
import com.speech.up.record.service.recordFile.RecordFile;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;

@Service
@RequiredArgsConstructor
public class RecordService {
	private final RecordRepository recordRepository;
	private final ScriptRepository scriptRepository;
	private final CustomLogger customLogger;

	private List<RecordEntity> getActiveRecordsByScriptId(Long scriptId){
		return recordRepository.findByScriptScriptIdAndIsUseTrue(scriptId);
	}

	private List<RecordGetDto.Response> mapToResponse(List<RecordEntity> records){
		return records.stream()
			.map(RecordGetDto.Response::getRecords)
			.collect(Collectors.toList());
	}

	public List<RecordGetDto.Response> getRecordList(Long scriptId) {
		List<RecordEntity> activityRecords = getActiveRecordsByScriptId(scriptId);
		return mapToResponse(activityRecords);
	}

	public RecordAddDto.Response addRecord(MultipartFile file, String languageCode, Long scriptId) throws
		IOException,
		UnsupportedAudioFileException {
		// scriptId로 ScriptEntity 를 조회합니다.
		ScriptEntity scriptEntity = scriptRepository.findById(scriptId).get();

		WavToRaw wavToRaw = new WavToRaw();
		byte[] audio = wavToRaw.convertToRawPcm(file);

		RecordAddDto.Request request = new RecordAddDto.Request(file, languageCode, scriptEntity);
		// RecordEntity 를 생성합니다.
		RecordEntity recordEntity = RecordEntity.create(audio, request, scriptEntity);
		return RecordAddDto.toEntity(recordRepository.save(recordEntity));
	}

	public RecordIsUseDto.Response deleteRecord(RecordIsUseDto.Request recordIsUseRequestDto) {
		RecordEntity recordEntity = RecordEntity.delete(recordIsUseRequestDto);
		return RecordIsUseDto.deleteRecord(recordRepository.save(recordEntity));
	}

}
