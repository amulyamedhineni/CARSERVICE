package com.cognizant.cs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cs.security.dto.AuthRequestdto;
import com.cognizant.cs.security.entities.UserCredentials;
import com.cognizant.cs.security.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
 private AuthService authservice;
 
	@Autowired
 private AuthenticationManager authmanage;
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredentials user) {
        return ResponseEntity.ok(authservice.saveUser(user));
    }
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequestdto authrequest)
	{
		Authentication authenticate=authmanage.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUserName(), authrequest.getPassword()));
		if(authenticate.isAuthenticated())
		{
			return authservice.generateToken(authrequest.getUserName());
		}
		else
		{
			throw new RuntimeException("Invlaid Access");
		}
	}
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token)
	{
		authservice.validateToken(token);
		return "token is valid";
	}
	
}
