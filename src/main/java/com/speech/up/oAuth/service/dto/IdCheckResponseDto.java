package com.speech.up.oAuth.service.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.speech.up.oAuth.common.ResponseCode;
import com.speech.up.oAuth.common.ResponseMessage;

import lombok.Getter;

@Getter
public class IdCheckResponseDto extends ResponseDto{
	private IdCheckResponseDto() {
		super();
	}

	public static ResponseEntity<IdCheckResponseDto> success() {
		IdCheckResponseDto idCheckResponseDto = new IdCheckResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(idCheckResponseDto);
	}

	public static ResponseEntity<ResponseDto> duplicateId() {
		return SignUpResponseDto.duplicated();
	}
}
