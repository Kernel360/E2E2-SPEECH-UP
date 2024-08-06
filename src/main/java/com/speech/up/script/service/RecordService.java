package com.speech.up.script.service;

import com.speech.up.script.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.repository.RecordRepository;
import com.speech.up.script.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.RecordGetDto;
import com.speech.up.script.service.dto.RecordIsUseDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.recordFile.RecordFile;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
	private final RecordRepository recordRepository;

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

	public RecordAddDto.Response addRecord(RecordAddDto.Request recordAddRequestDto) throws
		IllegalAccessException, IOException {
		RecordEntity recordEntity = RecordEntity.create(recordAddRequestDto);
		RecordFile recordFile = new RecordFile(recordAddRequestDto.getFile());
		recordFile.createFile(recordAddRequestDto.getAudioPath());
		return RecordAddDto.addRecord(recordRepository.save(recordEntity));
	}

	public RecordIsUseDto.Response deleteRecord(RecordIsUseDto.Request recordIsUseRequestDto) {
		RecordEntity recordEntity = RecordEntity.delete(recordIsUseRequestDto);
		return RecordIsUseDto.deleteRecord(recordRepository.save(recordEntity));
	}

}
