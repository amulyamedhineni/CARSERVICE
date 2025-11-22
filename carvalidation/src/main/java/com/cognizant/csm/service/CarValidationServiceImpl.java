package com.cognizant.csm.service;

import com.cognizant.csm.entity.CarValidationLog;
import com.cognizant.csm.repository.CarValidationRepository;
import com.cognizant.csm.dto.ValidationResponse;
import com.cognizant.csm.mapper.CarValidationMapper;
import com.cognizant.csm.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CarValidationServiceImpl implements CarValidationService {

    @Autowired
    private CarValidationRepository repository;

    @Autowired
    private CarValidationMapper mapper;

    @Override
    public boolean validateCarNumber(String request) {
        boolean isValid = request.matches("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");
        if (isValid) {
            CarValidationLog log =new CarValidationLog();
            log.setCarRegistrationNumber(request);
            log.setValidationTimestamp(LocalDateTime.now());
            repository.save(log);
            //return mapper.toResponse(log);
            return true;
        } else {
            throw new ResourceNotFoundException("Car Registration Number is Invalid " + request);
        }
    }

    @Override
    public ValidationResponse getValidationByCarNumber(String carRegistrationNumber) {
        // Fetch the latest validation log
        CarValidationLog log = repository.findTopByCarRegistrationNumberOrderByValidationTimestampDesc(carRegistrationNumber)
                                         .orElseThrow(() -> new ResourceNotFoundException("No validation found for car: " + carRegistrationNumber));
        return mapper.toResponse(log);
    }
}
