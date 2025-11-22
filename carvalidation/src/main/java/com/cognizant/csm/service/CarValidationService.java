package com.cognizant.csm.service;

import com.cognizant.csm.dto.ValidationResponse;

public interface CarValidationService {
  boolean validateCarNumber(String request);
   ValidationResponse getValidationByCarNumber(String carRegistrationNumber);
}

