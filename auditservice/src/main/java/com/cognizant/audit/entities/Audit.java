package com.cognizant.audit.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long auditID;
private Long carServiceID;
private Long userId;
private String action;
private LocalDateTime timeStamp;
private String performedBy;
private String details;

}
