package com.speech.up.globalException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
public class GlobalException extends Exception{


	@ExceptionHandler(MalformedURLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleMalformedURLException(MalformedURLException malformedURLException) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "URL 확인 필요! : " + malformedURLException.getMessage());
	}


	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "런타임 에러발생! : " + runtimeException.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException
		missingServletRequestParameterException) {
		return buildResponse(HttpStatus.BAD_REQUEST, "요청 값을 확인하세요 : " + missingServletRequestParameterException.getMessage());
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMissingServletRequestPartException(MissingServletRequestPartException
		missingServletRequestPartException) {
		return buildResponse(HttpStatus.BAD_REQUEST, "파일 값을 확인하세요 : " + missingServletRequestPartException.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException badRequestException) {
		return buildResponse(HttpStatus.BAD_REQUEST, "요청이 잘못되었습니다 : " +badRequestException.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		return buildResponse(HttpStatus.BAD_REQUEST, "매개변수가 정확하지 않습니다 : " +illegalArgumentException.getMessage());
	}

	@ExceptionHandler(ConfigDataResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ConfigDataResourceNotFoundException resourceNotFoundException){
		return buildResponse(HttpStatus.NOT_FOUND,"해당 파일이 존재하지 않습니다 : " + resourceNotFoundException.getMessage());
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleSQLException(SQLException sqlException) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "SQL 을 확인하세요! : " + sqlException.getSQLState());
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException nullPointerException) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "자료에 Null 값이 있습니다!! : " + nullPointerException.getMessage());
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleIOException(IOException ioException) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "IO 값에 문제가 있습니다: " + ioException.getMessage());
	}

	private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
		ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
		return new ResponseEntity<>(errorResponse, status);
	}
}
