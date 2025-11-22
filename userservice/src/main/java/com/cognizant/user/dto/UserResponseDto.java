package com.cognizant.user.dto;

import com.cognizant.user.entities.RoleStatus;
import com.cognizant.user.entities.UserPreference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
	private long userId;
	private String userName;
	private UserPreference preference;
	 private RoleStatus role;
}
