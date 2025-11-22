package com.cognizant.cs.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.cs.security.entities.UserCredentials;
import com.cognizant.cs.security.repositories.UserCredentialRepository;

@Service
public class AuthService {
	@Autowired
 private UserCredentialRepository userRepo;

	@Autowired
 private PasswordEncoder passEncode;
	
	@Autowired
  private JwtService jwtService;
	
	public String saveUser(UserCredentials userCred) {
	    boolean userExists = userRepo.existsByUserName(userCred.getUserName());

	    if (userExists) {
	        return "User already registered";
	    }

	    userCred.setPassword(passEncode.encode(userCred.getPassword()));
	    userRepo.save(userCred);
	    return "User added to system";
	}
	public String generateToken(String userName)
	{
		return jwtService.generateToken(userName);
	}
	public void validateToken(String token)
	{
		jwtService.validateToken(token);
	}
}
