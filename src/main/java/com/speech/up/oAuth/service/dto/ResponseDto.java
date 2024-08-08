package com.speech.up.oAuth.service.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.speech.up.oAuth.common.ResponseCode;
import com.speech.up.oAuth.common.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseDto {
	private String code;
	private String message;

	public ResponseDto() {
		this.code = ResponseCode.SUCCESS;
		this.message = ResponseMessage.SUCCESS;
	}

	public static ResponseEntity<ResponseDto> databaseError() {
		ResponseDto responseDto = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
	}

	public static ResponseEntity<ResponseDto> validationFail() {
		ResponseDto responseDto = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}
}
