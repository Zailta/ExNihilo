package com.blog.application.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.application.Exception.CustomExceptions.EXNResourceNotFoundException;
import com.blog.application.Utility.EXNAPIResponse;

@RestControllerAdvice
public class EXNGlobalExceptionHandler {
	
	@ExceptionHandler(EXNResourceNotFoundException.class)
	public ResponseEntity<EXNAPIResponse> resourceNotFoundException(EXNResourceNotFoundException ex){
		String message = ex.getMessage();
		EXNAPIResponse exnapiResponse = new EXNAPIResponse(message, false);
		return new ResponseEntity<>(exnapiResponse, HttpStatus.NOT_FOUND);
	}

	
}
