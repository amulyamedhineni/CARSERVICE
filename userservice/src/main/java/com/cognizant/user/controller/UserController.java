package com.cognizant.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.user.dto.UserDto;
import com.cognizant.user.dto.UserResponseDto;
import com.cognizant.user.entities.User;
import com.cognizant.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService serviceRepo;
	@PostMapping("/user")
  public ResponseEntity<?>addUser(@RequestBody UserDto userdto)
  {
	   User user=serviceRepo.addUser(userdto);
	   return new ResponseEntity<>(HttpStatus.CREATED).ok().body(user);
  }
	@GetMapping("/fetch/{userId}")
  public ResponseEntity<?>fetchUserDetails(@PathVariable long userId)
  {
	    UserResponseDto userresponsedto= serviceRepo.fetchUserDetails(userId);
	    return new ResponseEntity<>(HttpStatus.OK).ok().body(userresponsedto);
  }
	@PutMapping("/update/{userId}")
 public ResponseEntity<?>updateDetails(@PathVariable long userId,@RequestBody UserDto userdto )
 {
	 UserResponseDto userresponsedto=serviceRepo.updateDetails(userId, userdto);
	 return new ResponseEntity<>(HttpStatus.CREATED).ok().body(userresponsedto);
 }
	@DeleteMapping("/delete/{userId}")
 public ResponseEntity<?>deleteDetails(@PathVariable long userId)
 {
	       boolean isDeleted=serviceRepo.deleteDetails(userId);
	       if(isDeleted)
	       {
	    	   return new ResponseEntity<>(HttpStatus.ACCEPTED).ok().body("deletion succesfully");
	       }
	       else
	       {
	    	   return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED).ok().body("deletion unsuccesfully");
	       }
 }
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<?> getBydUserId(@PathVariable Long userId) {
	    UserResponseDto userresponsedto = serviceRepo.getUserById(userId);
	    return ResponseEntity.ok(userresponsedto);
	}
 
}
