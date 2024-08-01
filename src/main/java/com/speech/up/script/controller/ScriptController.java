package com.speech.up.script.controller;

import com.speech.up.script.service.ScriptService;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speech-script")
@RequiredArgsConstructor
public class ScriptController {
    private final ScriptService scriptService;

    /**
     * 유저의 대본 목록 조회
     * @param userId 유저의 PK ID
     * @return 대본 리스트 반환
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<ScriptGetDto.ScriptGetResponseDto>> getScriptAll(@PathVariable Long userId){
        return ResponseEntity.ok(scriptService.getScriptList(userId));
    }

    /**
     * 대본 생성
     * @param scriptAddRequestDto RequestBody 로 대본작성시 필요한 내용을 받아옴
     * @return 대본을 user 의 대본 목록에 추가
     */
    @PostMapping("")
    public ResponseEntity<ScriptAddDto.ScriptAddResponseDto> addScript(
            @RequestBody ScriptAddDto.ScriptAddRequestDto scriptAddRequestDto
    ) {
        return ResponseEntity.ok(scriptService.addScript(scriptAddRequestDto));
    }

    /**
     * 대본 수정 기능
     *
     * @param scriptUpdateRequestDto RequestBody 로 수정할 내용을 받아옴
     * @return 수정된 대본 반환
     */
    @PutMapping("")
    public ResponseEntity<ScriptUpdateDto.ScriptUpdateResponseDto> updateScript(
        @RequestBody ScriptUpdateDto.ScriptUpdateRequestDto scriptUpdateRequestDto
    ) {
         return ResponseEntity.ok(scriptService.updateScript(scriptUpdateRequestDto));
    } // 업데이트 시 내용 날아갈 수 있어 수정 필요

    /**
     * 대본 삭제 기능
     *
     * @param scriptIsUseRequestDto scriptId 에 해당하는 정보를 모두 삭제
     * @return 삭제 후 성공시 ok 응답
     */
    @PatchMapping("")
    public ResponseEntity<ScriptIsUseDto.ScriptIsUseResponseDto> deleteScript(@RequestBody ScriptIsUseDto.ScriptIsUseRequestDto scriptIsUseRequestDto) {
         scriptService.deleteScriptById(scriptIsUseRequestDto);
         return ResponseEntity.ok(scriptService.deleteScriptById(scriptIsUseRequestDto));
    }
}
