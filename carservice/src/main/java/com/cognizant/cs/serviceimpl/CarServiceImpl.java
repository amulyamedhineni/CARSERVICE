package com.cognizant.cs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.cs.dto.CarDto;
import com.cognizant.cs.entities.Car;
import com.cognizant.cs.exception.ResourceNotFoundException;
import com.cognizant.cs.mapper.CarMapper;
import com.cognizant.cs.repositories.CarRepository;
import com.cognizant.cs.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepo;

    @Override
    public void addCarDetails(CarDto carDto) {
        Car car = CarMapper.mapToCar(carDto);
        carRepo.save(car);
    }

    @Override
    public CarDto fetchCarDetails(Long carID) {
        Car car = carRepo.findById(carID)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "CarID", carID.toString()));
        return CarMapper.mapToCarDto(car);
    }

    @Override
    public CarDto updateDetails(Long carID, CarDto updateDto) {
        Car existingCar = carRepo.findById(carID)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "CarID", carID.toString()));

        existingCar.setCustomerID(updateDto.getCustomerID());
        existingCar.setServiceType(updateDto.getServiceType());
        existingCar.setServiceDate(updateDto.getServiceDate());
        existingCar.setCarStatus(updateDto.getCarStatus());

        Car updatedCar = carRepo.save(existingCar);
        return CarMapper.mapToCarDto(updatedCar);
    }

    @Override
    public boolean deleteDetails(Long carID) {
        Car car = carRepo.findById(carID)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "CarID", carID.toString()));
        carRepo.delete(car);
        return true;
    }
}
