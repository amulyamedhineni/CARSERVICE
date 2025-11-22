package com.cognizant.cs.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CarDto {
    @NotBlank(message = "Car registration number must not be blank")
    private String carRegistrationNumber;

    @NotNull(message = "Customer ID is required")
    private Long customerID;

    @NotBlank(message = "Service type must not be blank")
    private String serviceType;

    @NotNull(message = "Service date is required")
    private Date serviceDate;

    @NotBlank(message = "Car status must not be blank")
    private String carStatus;

    @NotBlank(message = "Car note must not be blank")
    private String carNote;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

