package com.speech.up.api.etri.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestPronunciationDto {
	private final String requestId;
	private final ArgumentDTO argument;

	@Getter
	@ToString
	@Builder
	public static class ArgumentDTO {
		private final String audio;
		private final String script;

		private ArgumentDTO(String audio, String script) {
			this.audio = audio;
			this.script = script;
		}
	}

	private RequestPronunciationDto(String requestId, ArgumentDTO argument) {
		this.requestId = requestId;
		this.argument = argument;
	}

	public static RequestPronunciationDto createPronunciation(String requestId, String audio, String script){
		ArgumentDTO argumentDTO = ArgumentDTO.builder()
			.audio(audio)
			.script(script)
			.build();

		return new RequestPronunciationDto(requestId, argumentDTO);
	}
}