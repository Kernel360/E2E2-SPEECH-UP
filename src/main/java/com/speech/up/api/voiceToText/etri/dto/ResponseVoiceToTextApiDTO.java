package com.speech.up.api.voiceToText.etri.dto;

import lombok.Data;

@Data
public class ResponseVoiceToTextApiDTO {
	private int result;
	private ReturnObjectDTO return_object;

	@Data
	public static class ReturnObjectDTO {
		private String recognized;  // 음성 인식 결과
	}
}
