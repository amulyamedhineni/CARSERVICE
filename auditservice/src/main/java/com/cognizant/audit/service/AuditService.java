package com.cognizant.audit.service;

import java.util.List;

import com.cognizant.audit.entities.Audit;

public interface AuditService {
    
    public Audit logaction(Long carServiceID, Long userId, String action, String performedBy, String details);

    public List<Audit> findByCarServiceId(Long carServiceID);
}
