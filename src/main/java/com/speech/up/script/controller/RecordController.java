package com.speech.up.script.controller;

import com.speech.up.script.entity.RecordEntity;
import com.speech.up.script.service.RecordService;
import com.speech.up.script.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.RecordGetDto;
import com.speech.up.script.service.dto.RecordIsUseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speech-record")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/{scriptId}")
    public ResponseEntity<List<RecordGetDto.RecordGetResponseDto>> getRecordALl(@PathVariable Long scriptId){
        return ResponseEntity.ok(recordService.getRecordList(scriptId));
    }

    @PostMapping("")
    public ResponseEntity<RecordAddDto.RecordAddResponseDto> addRecord(
            @RequestBody RecordAddDto.RecordAddRequestDto recordAddRequestDto
    ) {
        return ResponseEntity.ok(recordService.addRecord(recordAddRequestDto));
    }

    @PatchMapping("")
    public ResponseEntity<RecordIsUseDto.RecordIsUseResponseDto> deleteRecord(
            @RequestBody RecordIsUseDto.RecordIsUseRequestDto recordIsUseRequestDto
    ) {
        return ResponseEntity.ok(recordService.deleteRecord(recordIsUseRequestDto));
    }
}
