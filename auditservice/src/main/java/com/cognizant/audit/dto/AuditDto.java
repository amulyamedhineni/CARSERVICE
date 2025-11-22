package com.cognizant.audit.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuditDto {
private Long carServiceID;
private Long userId;
private String action;
private LocalDateTime timeStamp;
private String performedBy;
private String details;
}
