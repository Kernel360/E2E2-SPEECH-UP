package com.speech.up.api.voiceToText.etri.dto;

import lombok.Data;

@Data
public class RequestVoiceToTextApiDTO {
	private String request_id;
	private ArgumentDTO argument;

	@Data
	public static class ArgumentDTO {
		private String language_code;
		private String audio;
	}
}
