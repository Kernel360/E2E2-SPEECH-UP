package com.speech.up.script.service;

import com.speech.up.script.entity.RecordEntity;
import com.speech.up.script.repository.RecordRepository;
import com.speech.up.script.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.RecordGetDto;
import com.speech.up.script.service.dto.RecordIsUseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public List<RecordGetDto.RecordGetResponseDto> getRecordList(Long scriptId) {
        return recordRepository.findByScriptScriptIdAndIsUseTrue(scriptId)
                .stream()
                .map(RecordGetDto.RecordGetResponseDto::getRecords)
                .collect(Collectors.toList());
    }

    public RecordAddDto.RecordAddResponseDto addRecord(RecordAddDto.RecordAddRequestDto recordAddRequestDto) {
        RecordEntity recordEntity = new RecordEntity(recordAddRequestDto);
        return RecordAddDto.RecordAddResponseDto.addRecord(recordRepository.save(recordEntity));
    }

    public RecordIsUseDto.RecordIsUseResponseDto deleteRecord(RecordIsUseDto.RecordIsUseRequestDto recordIsUseRequestDto) {
        RecordEntity recordEntity = new RecordEntity(recordIsUseRequestDto);
        return RecordIsUseDto.RecordIsUseResponseDto.deleteRecord(recordRepository.save(recordEntity));
    }
}