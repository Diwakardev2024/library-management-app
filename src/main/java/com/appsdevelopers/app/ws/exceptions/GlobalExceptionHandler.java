package com.appsdevelopers.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.appsdevelopers.app.ws.ui.model.response.ErrorMessage;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request)
	{
		ErrorMessage errorMessagge=new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessagge,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	

}
