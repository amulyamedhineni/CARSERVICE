package com.cognizant.cs.mapper;

import com.cognizant.cs.dto.CarDto;
import com.cognizant.cs.entities.Car;

public class CarMapper {

	public static CarDto mapToCarDto(Car car) {
	    CarDto dto = new CarDto();
	    dto.setCarRegistrationNumber(car.getCarRegistrationNumber());
	    dto.setCustomerID(car.getCustomerID());
	    dto.setServiceType(car.getServiceType());
	    dto.setServiceDate(car.getServiceDate());
	    dto.setCarStatus(car.getCarStatus());
	    dto.setCarNote(car.getCarNote());
	    dto.setCreatedAt(car.getCreatedAt());
	    dto.setUpdatedAt(car.getUpdatedAt());
	    return dto;
	}


	public static Car mapToCar(CarDto dto) {
	    Car car = new Car();
	    car.setCarRegistrationNumber(dto.getCarRegistrationNumber());
	    car.setCustomerID(dto.getCustomerID());
	    car.setServiceType(dto.getServiceType());
	    car.setServiceDate(dto.getServiceDate());
	    car.setCarStatus(dto.getCarStatus());
	    car.setCarNote(dto.getCarNote());
	    return car;
	}

}
