package com.speech.up.oAuth.service.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.speech.up.oAuth.common.ResponseCode;
import com.speech.up.oAuth.common.ResponseMessage;

import lombok.Getter;

@Getter
public class SignUpResponseDto extends ResponseDto {
	private SignUpResponseDto() {
		super();
	}

	public static ResponseEntity<SignUpResponseDto> success() {
		SignUpResponseDto responseDto = new SignUpResponseDto();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	public static ResponseEntity<ResponseDto> duplicated() {
		ResponseDto responseDto = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}

	public static ResponseEntity<ResponseDto> certificationFail() {
		ResponseDto responseDto = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
	}
}
