package com.speech.up.api.etri.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseRecognitionDto {
	private String request_id;
	private int result;
	private ReturnObjectDTO return_object;

	@Getter
	@ToString
	public static class ReturnObjectDTO {
		private String recognized;
	}
}
