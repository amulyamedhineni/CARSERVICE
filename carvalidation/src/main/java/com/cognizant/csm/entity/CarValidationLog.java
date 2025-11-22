package com.cognizant.csm.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="car_validation_logs")
public class CarValidationLog {
	
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     
     @Column(name="car_registration_number")
     private String carRegistrationNumber;
     
     @Column(name="is_valid")
     private boolean isValid;
     
     @Column(name="validation_timestamp")
     private LocalDateTime validationTimestamp;
     
}
