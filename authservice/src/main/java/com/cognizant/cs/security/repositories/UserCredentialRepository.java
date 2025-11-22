package com.cognizant.cs.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.cs.security.entities.UserCredentials;

public interface UserCredentialRepository extends JpaRepository<UserCredentials, Long> {


	Optional<UserCredentials> findByuserName(String username);

	boolean existsByUserName(String userName);


}
