package com.cognizant.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.user.dto.UserDto;
import com.cognizant.user.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDto findByuserId(Long userId);

}
