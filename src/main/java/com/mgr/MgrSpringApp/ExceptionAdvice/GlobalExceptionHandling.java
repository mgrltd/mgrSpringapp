package com.mgr.MgrSpringApp.ExceptionAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<String> nullPointerException()
	{ 
		return new ResponseEntity<String>("value is null",HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
	public ResponseEntity<String> arrayIndexOutOfBoundsException()
	{
		return new ResponseEntity<String>("The array index is out of bound",HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
}
