package com.speech.up.record.controller;

import com.speech.up.record.service.RecordService;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * RecordController는 스피치 녹음과 관련된 API 엔드포인트를 제공합니다.
 * 이 컨트롤러는 녹음의 조회, 추가, 삭제 및 분석 여부를 처리합니다.
 * <p>
 * 모든 엔드포인트는 인증된 사용자(GENERAL_USER 또는 ADMIN_USER)만 접근할 수 있습니다.
 * </p>
 */
@RestController
@RequestMapping("/speech-record")
@RequiredArgsConstructor
public class RecordController {

	private final RecordService recordService;

	/**
	 * 특정 스크립트 ID에 해당하는 모든 녹음을 조회합니다.
	 *
	 * @param scriptId 조회할 스크립트의 ID
	 * @return 스크립트에 연결된 녹음 목록
	 */
	@GetMapping("/{scriptId}")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<List<RecordGetDto.Response>> getRecordALl(@PathVariable Long scriptId) {
		return ResponseEntity.ok(recordService.getRecordList(scriptId));
	}

	/**
	 * 새로운 녹음을 추가합니다.
	 *
	 * @param file         업로드할 오디오 파일 (MultipartFile 형식)
	 * @param languageCode 오디오 파일의 언어 코드
	 * @param scriptId     연결할 스크립트의 ID
	 * @return 추가된 녹음의 세부 정보
	 * @throws IOException                     파일 입출력 중 오류 발생 시
	 * @throws UnsupportedAudioFileException   지원되지 않는 오디오 파일 형식일 경우
	 */
	@PostMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<RecordAddDto.Response> addRecord(
		@RequestPart("file") MultipartFile file,
		@RequestParam("languageCode") String languageCode,
		@RequestParam("scriptId") Long scriptId
	) throws IOException, UnsupportedAudioFileException {
		RecordAddDto.Response response = recordService.addRecord(file, languageCode, scriptId);
		return ResponseEntity.ok(response);
	}

	/**
	 * 특정 녹음을 삭제합니다. 실제로 데이터를 삭제하지 않고, 삭제 상태로 표시합니다.
	 *
	 * @param recordId 삭제할 녹음의 ID
	 * @return 삭제 상태로 변경된 녹음의 세부 정보
	 */
	@PatchMapping("/{recordId}")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<RecordIsUseDto.Response> deleteRecord(
		@PathVariable Long recordId
	) {
		return ResponseEntity.ok(recordService.deleteRecord(recordId));
	}

	/**
	 * 특정 녹음의 분석 여부를 설정합니다.
	 *
	 * @param recordId 분석 여부를 설정할 녹음의 ID
	 * @return 처리 성공 시 빈 응답 반환
	 */
	@PatchMapping("/{recordId}/analyze")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<Void> isRecordAnalyzed(
		@PathVariable Long recordId
	) {
		recordService.analyzed(recordId);
		return ResponseEntity.ok().build();
	}
}
