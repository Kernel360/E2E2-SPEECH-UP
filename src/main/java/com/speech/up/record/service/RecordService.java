package com.speech.up.record.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.speech.up.api.converter.WavToRaw;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.record.repository.RecordRepository;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.ScriptRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordService {
	private final RecordRepository recordRepository;
	private final ScriptRepository scriptRepository;

	private List<RecordEntity> getActiveRecordsByScriptId(Long scriptId) {
		return recordRepository.findByScriptScriptIdAndIsUseTrue(scriptId);
	}

	private List<RecordGetDto.Response> mapToResponse(List<RecordEntity> records) {
		return records.stream()
			.map(RecordGetDto.Response::toResponse)
			.collect(Collectors.toList());
	}

	public List<RecordGetDto.Response> getRecordList(Long scriptId) {
		List<RecordEntity> activityRecords = getActiveRecordsByScriptId(scriptId);
		return mapToResponse(activityRecords);
	}

	public RecordAddDto.Response addRecord(MultipartFile file, String languageCode, Long scriptId)
		throws IOException, UnsupportedAudioFileException {

		ScriptEntity scriptEntity = scriptRepository.findById(scriptId)
			.orElseThrow(() -> new EntityNotFoundException("script not found by scriptId : " + scriptId));

		WavToRaw wavToRaw = new WavToRaw();
		Optional<byte[]> audio = wavToRaw.convertToRawPcm(file);
		RecordAddDto.Request request = new RecordAddDto.Request(file, languageCode, scriptEntity);
		RecordEntity recordEntity = RecordEntity.create(audio.orElse(null), request, scriptEntity);

		return RecordAddDto.toResponse(recordRepository.save(recordEntity));
	}

	public RecordIsUseDto.Response deleteRecord(RecordIsUseDto.Request recordIsUseRequestDto) {
		RecordEntity recordEntity = RecordEntity.delete(recordIsUseRequestDto);
		return RecordIsUseDto.toResponse(recordRepository.save(recordEntity));
	}

	@Transactional
	public void analyzed(Long recordId) {
		RecordEntity recordEntity = recordRepository.findById(recordId)
			.orElseThrow(() -> new IllegalStateException("not found record by recordId : " + recordId));
		recordRepository.save(RecordEntity.analyze(recordEntity));
	}

}
