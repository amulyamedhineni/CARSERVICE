package com.cognizant.audit.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.audit.entities.Audit;
import com.cognizant.audit.repositories.AuditRepository;
import com.cognizant.audit.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditrepo;

    @Override
    public Audit logaction(Long carServiceID, Long userId, String action, String performedBy, String details) {
        Audit au = new Audit();
        au.setCarServiceID(carServiceID);
        au.setUserId(userId); // ✅ now saving userId too
        au.setAction(action);
        au.setTimeStamp(LocalDateTime.now());
        au.setPerformedBy(performedBy);
        au.setDetails(details);

        return auditrepo.save(au);
    }

    @Override
    public List<Audit> findByCarServiceId(Long carServiceID) {
        return auditrepo.findBycarServiceID(carServiceID);
    }
}
