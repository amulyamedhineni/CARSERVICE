package com.cognizant.user.service;

import com.cognizant.user.dto.UserDto;
import com.cognizant.user.dto.UserResponseDto;
import com.cognizant.user.entities.User;

public interface UserService {
	public User addUser(UserDto userdto);
	public UserResponseDto fetchUserDetails(Long userId);
	public UserResponseDto updateDetails(Long userId, UserDto userdto);
	public boolean deleteDetails(Long userId);
	public UserResponseDto getUserById(Long userId) ;
	
}
