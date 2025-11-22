package com.cognizant.csm.repository;

import com.cognizant.csm.entity.CarValidationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarValidationRepository extends JpaRepository<CarValidationLog, Long> {
    Optional<CarValidationLog> findTopByCarRegistrationNumberOrderByValidationTimestampDesc(String carRegistrationNumber);
}
