package com.cognizant.csm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
	private String carRegistrationNumber;
    private boolean isValid;
    private LocalDateTime validationTimestamp;
}
