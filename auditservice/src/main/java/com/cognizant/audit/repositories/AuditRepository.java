package com.cognizant.audit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.audit.entities.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {

	List<Audit> findBycarServiceID(long carServiceID);

	List<Audit> findByuserId(long userId);

}
