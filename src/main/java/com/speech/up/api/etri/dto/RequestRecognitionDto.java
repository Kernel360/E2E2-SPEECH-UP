package com.speech.up.api.etri.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestRecognitionDto {
	private final String requestId;
	private final ArgumentDTO argument;

	@Getter
	@Builder
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class ArgumentDTO {
		private final String language_code;
		private final String audio;

		private ArgumentDTO(String languageCode, String audio) {
			this.language_code = languageCode;
			this.audio = audio;
		}
	}

	private RequestRecognitionDto(String requestId, ArgumentDTO argument) {
		this.requestId = requestId;
		this.argument = argument;
	}

	public static RequestRecognitionDto createRecognition(String requestId, String audio, String languageCode){
		ArgumentDTO argumentDTO = ArgumentDTO.builder()
			.language_code(languageCode)
			.audio(audio)
			.build();

		return new RequestRecognitionDto(requestId, argumentDTO);
	}
}
