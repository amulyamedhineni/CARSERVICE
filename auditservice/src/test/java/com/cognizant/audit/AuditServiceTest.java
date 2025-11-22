package com.cognizant.audit;

import com.cognizant.audit.entities.Audit;
import com.cognizant.audit.repositories.AuditRepository;
import com.cognizant.audit.serviceimpl.AuditServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditServiceTest {

    @InjectMocks
    private AuditServiceImpl auditService;

    @Mock
    private AuditRepository auditRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogAction() {
        // Arrange
        long carServiceID = 1L;
        long userId = 101L;
        String action = "CREATE";
        String performedBy = "admin";
        String details = "Created new service entry";

        Audit mockAudit = new Audit();
        mockAudit.setAuditID(100L);
        mockAudit.setCarServiceID(carServiceID);
        mockAudit.setUserId(userId);
        mockAudit.setAction(action);
        mockAudit.setTimeStamp(LocalDateTime.now());
        mockAudit.setPerformedBy(performedBy);
        mockAudit.setDetails(details);

        when(auditRepository.save(any(Audit.class))).thenReturn(mockAudit);

        // Act
        Audit result = auditService.logaction(carServiceID, userId, action, performedBy, details);

        // Assert
        assertNotNull(result);
        assertEquals(carServiceID, result.getCarServiceID());
        assertEquals(userId, result.getUserId());
        assertEquals(action, result.getAction());
        assertEquals(performedBy, result.getPerformedBy());
        assertEquals(details, result.getDetails());

        verify(auditRepository, times(1)).save(any(Audit.class));
    }

    @Test
    void testFindByCarServiceId() {
        // Arrange
        long carServiceID = 1L;
        Audit audit1 = new Audit(1L, carServiceID, 101L, "CREATE", LocalDateTime.now(), "admin", "details1");
        Audit audit2 = new Audit(2L, carServiceID, 102L, "UPDATE", LocalDateTime.now(), "admin", "details2");

        when(auditRepository.findBycarServiceID(carServiceID)).thenReturn(Arrays.asList(audit1, audit2));

        // Act
        List<Audit> result = auditService.findByCarServiceId(carServiceID);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(101L, result.get(0).getUserId());
        assertEquals(102L, result.get(1).getUserId());
        verify(auditRepository, times(1)).findBycarServiceID(carServiceID);
    }
}
