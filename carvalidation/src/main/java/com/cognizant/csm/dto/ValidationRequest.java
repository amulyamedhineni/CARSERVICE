package com.cognizant.csm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidationRequest {
	@NotNull(message = "carRegisterationNumber is required")
  private String carRegistrationNumber;
}
