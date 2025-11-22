package com.cognizant.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognizant.cs.dto.CarDto;
import com.cognizant.cs.service.CarService;

@RestController
@RequestMapping("/api/carservice")
public class CarServiceController {

    @Autowired
    private CarService servicerepo;

    @PostMapping("/car")
    public ResponseEntity<?> addCarDetails(@RequestBody CarDto carDto) {
        servicerepo.addCarDetails(carDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Car Details Added Successfully");
    }

    @GetMapping("/fetch/{carID}")
    public ResponseEntity<?> fetchCarDetails(@PathVariable Long carID) {
        CarDto cardto = servicerepo.fetchCarDetails(carID);
        return ResponseEntity.status(HttpStatus.OK).body(cardto);
    }

    @PutMapping("/update/{carID}")
    public ResponseEntity<?> updateDetails(@PathVariable Long carID, @RequestBody CarDto cardto) {
        CarDto updatedDto = servicerepo.updateDetails(carID, cardto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/delete/{carID}")
    public ResponseEntity<?> deleteDetails(@PathVariable Long carID) {
        boolean isDeleted = servicerepo.deleteDetails(carID);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Deletion Unsuccessful");
        }
    }
}
