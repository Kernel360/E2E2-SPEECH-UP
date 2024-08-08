package com.speech.up.oAuth.service.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.speech.up.oAuth.common.ResponseCode;
import com.speech.up.oAuth.common.ResponseMessage;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto{

	private final String token;
	private final int expirationTime;

	private SignInResponseDto(String token) {
		super();
		this.token = token;
		this.expirationTime = 3600;
	}

	public static ResponseEntity<SignInResponseDto> success(String token) {
		SignInResponseDto responseDto = new SignInResponseDto(token);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	public static ResponseEntity<ResponseDto> signInFail() {
		ResponseDto responseDto = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
	}
}
