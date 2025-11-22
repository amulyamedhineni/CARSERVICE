package com.cognizant.audit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.audit.dto.AuditDto;
import com.cognizant.audit.entities.Audit;
import com.cognizant.audit.service.AuditService;

@RestController
@RequestMapping("/api")
public class AuditController {
	@Autowired
 private AuditService servicerepo;
	@PostMapping("/create")
	public ResponseEntity<?>logAction(@RequestBody AuditDto auditrequest)
	{
		Audit au=servicerepo.logaction(auditrequest.getCarServiceID(),auditrequest.getUserId(),auditrequest.getAction(),
				 auditrequest.getPerformedBy()
				  ,auditrequest.getDetails());
		  return ResponseEntity.ok().body(au);
	}
	@GetMapping("/fetchlogs/{carServiceID}")
	public ResponseEntity<?>getDetails(@PathVariable Long carServiceID)
	{
		List<Audit> auditList=servicerepo.findByCarServiceId(carServiceID);
		return ResponseEntity.ok().body(auditList);
	}
	
 
}
