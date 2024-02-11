package com.Blog_Application.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Blog_Application.Payload.apiResponse;

@RestControllerAdvice
public class GlobleControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<apiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		apiResponse apiresponse = new apiResponse(message,false);
		return new ResponseEntity<apiResponse>(apiresponse,HttpStatus.NOT_FOUND);
	}
}
