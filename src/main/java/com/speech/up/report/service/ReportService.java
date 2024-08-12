package com.speech.up.report.service;

import org.springframework.stereotype.Service;

import com.speech.up.api.speechFlow.service.VoiceToTextSpeechFlowService;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.entity.type.ReportContentAndScore;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.report.service.dto.ReportAddDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
	private final VoiceToTextSpeechFlowService voiceToTextSpeechFlowService;
	private final ReportRepository reportRepository;

	public ReportAddDto.Response getReport(String filePath, Long recordId, ReportAddDto.Request request) {
		try{
			// AI 요청하기
			String taskResult = voiceToTextSpeechFlowService.getResult(filePath);
			//분석하기 코드
			//String content = Analytics.getContent();
			//Double score = Analytics.getScore();
		}catch (Exception e) {
			throw new IllegalArgumentException("AI API 실패 : " + e);
		}
		//수정된 컨텐츠과 점수 전달하기
		ReportContentAndScore reportContentAndScore =
			new ReportContentAndScore("content", 3.2, recordId);

		return ReportAddDto.toResponse(reportRepository.save(ReportEntity.create(reportContentAndScore,request)));
	}

}
