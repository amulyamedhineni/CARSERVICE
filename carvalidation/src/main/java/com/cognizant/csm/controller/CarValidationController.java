package com.cognizant.csm.controller;

import com.cognizant.csm.dto.ValidationResponse;
import com.cognizant.csm.service.CarValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarValidationController {

    @Autowired
    private CarValidationService validationService;

    @GetMapping("/validate")
    public boolean validateCar(@RequestParam String request) {
        boolean response = validationService.validateCarNumber(request);
        return response;
    }

    @GetMapping("/validations/{carRegistrationNumber}")
    public ResponseEntity<ValidationResponse> getValidationByCar(@PathVariable String carRegistrationNumber) {
        ValidationResponse response = validationService.getValidationByCarNumber(carRegistrationNumber);
        return ResponseEntity.ok(response);
    }
}

