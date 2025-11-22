package com.cognizant.user.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.user.dto.UserDto;
import com.cognizant.user.dto.UserResponseDto;
import com.cognizant.user.entities.RoleStatus;
import com.cognizant.user.entities.User;
import com.cognizant.user.entities.UserPreference;
import com.cognizant.user.exception.UserNotFoundException;
import com.cognizant.user.mapper.UserMapper;
import com.cognizant.user.repositories.UserRepository;
import com.cognizant.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User addUser(UserDto userdto) {
		User user = userRepo.save(createUser(userdto));
		return user;

	}

	private User createUser(UserDto userdto) {
		User user = new User();
		if (user.getPrefernce()== null) {
	        user.setPrefernce(new UserPreference());
	    }
		UserMapper.maptoUser(userdto, user);
		user.setRole(RoleStatus.USER);
		user.setCreatedAt(LocalDateTime.now());
		return user;

	}

	@Override
	public UserResponseDto fetchUserDetails(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user not found with this id"));
		UserResponseDto userresponsedto = UserMapper.mapToUserResponseDto(user, new UserResponseDto());
		return userresponsedto;
	}

	@Override
	public UserResponseDto updateDetails(Long userId, UserDto userdto) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user not exists with this id"));
		UserMapper.maptoUser(userdto, user);
		userRepo.save(user);
		UserResponseDto userresponsedto = UserMapper.mapToUserResponseDto(user, new UserResponseDto());
		return userresponsedto;
	}

	@Override
	public boolean deleteDetails(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user not exits with this id"));
		userRepo.deleteById(userId);
		return true;
	}

	@Override
	public UserResponseDto getUserById(Long userId) {
		   User user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("user not exits with this id"));
		  UserResponseDto userresponseDto= UserMapper.mapToUserResponseDto(user, new UserResponseDto());
		   return userresponseDto;
	}

}
