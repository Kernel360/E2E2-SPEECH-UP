package com.speech.up.common.exception.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.speech.up.common.dto.ApiExceptionResponse;
import com.speech.up.common.exception.custom.CustomIOException;
import com.speech.up.common.exception.custom.CustomIllegalArgumentException;
import com.speech.up.common.exception.custom.CustomRuntimeException;
import com.speech.up.common.exception.http.HttpBaseException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException exception) {
		ApiExceptionResponse<Void> responseDto = ApiExceptionResponse.<Void>builder()
			.statusCode(400)
			.message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
			.build();

		return new ResponseEntity<>(
			responseDto,
			HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(HttpBaseException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleHttpException(HttpBaseException exception) {
		ApiExceptionResponse<Void> responseDto = ApiExceptionResponse.<Void>builder()
			.statusCode(exception.getStatusCode().value())
			.message(exception.getMessage())
			.build();

		return new ResponseEntity<>(
			responseDto,
			exception.getStatusCode()
		);
	}

	@ExceptionHandler(CustomRuntimeException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleCustomException(CustomRuntimeException exception) {
		ApiExceptionResponse<Void> responseDto = ApiExceptionResponse.<Void>builder()
			.statusCode(exception.getErrorCode().getCode())
			.message(exception.getErrorCode().getMessage())
			.build();

		return new ResponseEntity<>(
			responseDto,
			exception.getStatusCode()
		);
	}

	@ExceptionHandler(CustomIllegalArgumentException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleCustomException(CustomIllegalArgumentException exception) {
		ApiExceptionResponse<Void> responseDto = ApiExceptionResponse.<Void>builder()
			.statusCode(exception.getErrorCode().getCode())
			.message(exception.getErrorCode().getMessage())
			.build();

		return new ResponseEntity<>(
			responseDto,
			HttpStatusCode.valueOf(exception.getErrorCode().getCode())
		);
	}

	@ExceptionHandler(CustomIOException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleCustomException(CustomIOException exception) {
		ApiExceptionResponse<Void> responseDto = ApiExceptionResponse.<Void>builder()
			.statusCode(exception.getErrorCode().getCode())
			.message(exception.getErrorCode().getMessage())
			.build();

		return new ResponseEntity<>(
			responseDto,
			HttpStatusCode.valueOf(exception.getErrorCode().getCode())
		);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleNullPointerException(NullPointerException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiExceptionResponse<Void>> handleIOException(IOException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
