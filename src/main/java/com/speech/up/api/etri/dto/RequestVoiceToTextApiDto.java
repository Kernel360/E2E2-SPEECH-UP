package com.speech.up.api.etri.dto;

import lombok.Builder;
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