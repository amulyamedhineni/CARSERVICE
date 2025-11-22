package com.cognizant.csm.mapper;

import org.springframework.stereotype.Component;

import com.cognizant.csm.dto.ValidationRequest;
import com.cognizant.csm.dto.ValidationResponse;
import com.cognizant.csm.entity.CarValidationLog;

@Component
public class CarValidationMapper {
    public static CarValidationLog toEntity(ValidationRequest request,boolean isValid) {
    	CarValidationLog log = new CarValidationLog();
    	log.setCarRegistrationNumber(request.getCarRegistrationNumber());
    	log.setValid(isValid);
    	log.setValidationTimestamp(java.time.LocalDateTime.now());
    	return log;
    }
    
    public static ValidationResponse toResponse(CarValidationLog entity) {
    	ValidationResponse response = new ValidationResponse();
    	response.setCarRegistrationNumber(entity.getCarRegistrationNumber());
    	response.setValid(entity.isValid());
    	response.setValidationTimestamp(entity.getValidationTimestamp());
    	return response;

    }
}
