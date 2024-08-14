package com.speech.up.api.etri.dto;

import com.speech.up.api.etri.type.ApiType;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class RequestVoiceToTextApiDto {
	private String request_id;
	private ArgumentDTO argument;

	@Getter
	@Builder
	public static class ArgumentDTO {
		private String language_code;
		private String audio;
		private String script;
	}
}