package com.cognizant.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String resourceName, String feildName, String feildValue) {
      super(String.format(resourceName,feildName,feildValue));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
