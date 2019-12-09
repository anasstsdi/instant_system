package com.parking.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.parking.requestDto.GenericExceptionResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleException(ResourceNotFoundException ex, WebRequest request) throws Exception {
		GenericExceptionResponseDto nevEx = new GenericExceptionResponseDto(ex.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));

		return new ResponseEntity<Object>(nevEx, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
		GenericExceptionResponseDto nevEx = new GenericExceptionResponseDto(ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));

		return new ResponseEntity<Object>(nevEx, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}