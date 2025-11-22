package com.cognizant.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.user.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<?>handleResourceNotFoundException(ResourceNotFoundException Exception,WebRequest webrequest)
	 {
		 ErrorResponseDTO errdt=new ErrorResponseDTO (webrequest.getDescription(false),HttpStatus.NOT_FOUND,Exception.getMessage(),LocalDateTime.now());
		 return new ResponseEntity<>(errdt,HttpStatus.NOT_FOUND);
	 }
		@ExceptionHandler(Exception.class)
		
		public ResponseEntity<?>handleGlobalException(Exception exception,WebRequest webRequest)
		{
			ErrorResponseDTO errdt=new ErrorResponseDTO (webRequest.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage(),LocalDateTime.now());
			return new ResponseEntity<>(errdt,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(UserNotFoundException.class)
		 public ResponseEntity<?>handleUserNotFoundException(UserNotFoundException Exception,WebRequest webrequest)
		 {
			 ErrorResponseDTO errdt=new ErrorResponseDTO (webrequest.getDescription(false),HttpStatus.NOT_FOUND,Exception.getMessage(),LocalDateTime.now());
			 return new ResponseEntity<>(errdt,HttpStatus.NOT_FOUND);
		 }
		
}
