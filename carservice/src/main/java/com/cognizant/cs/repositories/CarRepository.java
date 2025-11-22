package com.cognizant.cs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.cs.entities.Car;

public interface CarRepository extends JpaRepository<Car,Long> {

}
