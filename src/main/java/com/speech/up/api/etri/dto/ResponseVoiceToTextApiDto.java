package com.speech.up.api.etri.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseVoiceToTextApiDto{
	private int result;
	private ReturnObjectDTO return_object;

	@Getter
	@ToString
	public static class ReturnObjectDTO {
		private String recognized;
		private String score;// 음성 인식 결과
	}
}