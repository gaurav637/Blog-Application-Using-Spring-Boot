package com.Blog_Application.Exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotFoundException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldValue = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldValue, message);
		});
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		String string = "this http Method  is not supported.   --- your request is not support your http request in your controller class ";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(string);
	}
}
