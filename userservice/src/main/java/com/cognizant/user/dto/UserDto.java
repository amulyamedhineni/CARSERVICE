package com.cognizant.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
	@NotEmpty(message = "userName should required")
 private String userName;
 private String theme;
 @Column(name = "notifications")
 private boolean notifications;

}
