package com.cognizant.cs.service;

import org.springframework.stereotype.Service;
import com.cognizant.cs.dto.CarDto;

@Service
public interface CarService {
    void addCarDetails(CarDto carDto);
    CarDto fetchCarDetails(Long carID);
    CarDto updateDetails(Long carID, CarDto update);
    boolean deleteDetails(Long carID);
}
