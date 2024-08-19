package com.speech.up.api.etri.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestRecognizedDto {
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

	private RequestRecognizedDto(String requestId, ArgumentDTO argument) {
		this.requestId = requestId;
		this.argument = argument;
	}

	public static RequestRecognizedDto createRec(String requestId, String audio, String languageCode){
		ArgumentDTO argumentDTO = ArgumentDTO.builder()
			.language_code(languageCode)
			.audio(audio)
			.build();

		return new RequestRecognizedDto(requestId, argumentDTO);
	}
}
