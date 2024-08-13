package com.speech.up.api.etri.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.api.etri.type.ApiType;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AiRequest {
	private String requestId;
	private String filePath;
	private ApiType apiType;
	private String languageCode;
	private String script;
}
