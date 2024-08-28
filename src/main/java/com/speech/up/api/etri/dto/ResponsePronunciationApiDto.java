package com.speech.up.api.etri.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponsePronunciationApiDto {
	private String request_id;
	private int result;
	private ReturnObjectDTO return_object;

	@Getter
	@ToString
	public static class ReturnObjectDTO {
		private String recognized;
		private Double score;// 음성 인식 결과
	}
	public ResponsePronunciationApiDto(String request_id, int result, ReturnObjectDTO return_object) {
		this.request_id = request_id;
		this.result = result;
		this.return_object = return_object;
	}
}