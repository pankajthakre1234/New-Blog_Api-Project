package com.BikkadIT.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.BikkadIT.blog.helper.ApiResponce;
import com.BikkadIT.blog.helper.AppConstant;

@RestControllerAdvice
public class GlobalLevelException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandel(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		
		ApiResponce apiResponce=new ApiResponce(message, false);
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex)
	{
		Map<String, String> map=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName = ((FieldError)error).getField();
			
			String defaultMessage = error.getDefaultMessage();
			
			map.put(fieldName, defaultMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponce> handleApiException(ApiException ex) {
		String message = ex.getMessage();
		ApiResponce apiResponse = new ApiResponce(message, true);
		return new ResponseEntity<ApiResponce>(apiResponse, HttpStatus.BAD_REQUEST);
	}


}
