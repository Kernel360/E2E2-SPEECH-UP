package com.speech.up.api.etri.dto;

import lombok.Data;

@Data
public class ResponseVoiceToTextApiDto{
	private int result;
	private ReturnObjectDTO return_object;

	@Data
	public static class ReturnObjectDTO {
		private String recognized;
		private String score;// 음성 인식 결과
	}
}