package com.cognizant.user.mapper;

import com.cognizant.user.dto.UserDto;
import com.cognizant.user.dto.UserResponseDto;
import com.cognizant.user.entities.User;
import com.cognizant.user.entities.UserPreference;


public class UserMapper {
	
 public static UserDto mapToUserDto(User user,UserDto userdto)
 {
	
	 UserPreference userprefernce=user.getPrefernce();
	 userdto.setUserName(user.getUserName());
	 if(userprefernce!=null) {
	 userdto.setNotifications(userprefernce.isNotifications());
	 userdto.setTheme(userprefernce.getTheme());
	 }
	 return userdto;
 }
 
 public static UserResponseDto mapToUserResponseDto(User user, UserResponseDto userResponseDto) {
	 UserPreference userPreference = user.getPrefernce(); 

	    userResponseDto.setUserId(user.getUserId());
	    userResponseDto.setUserName(user.getUserName());
	    userResponseDto.setRole(user.getRole());

	    if (userPreference != null) {
	        userResponseDto.setPreference(userPreference);
	    }

	    return userResponseDto;
	}


 public static User maptoUser(UserDto userdto,User user)
 {
	 user.setUserName(userdto.getUserName());
	 UserPreference userprefernce=user.getPrefernce();
	 if(userprefernce!=null)
	 {
	 userprefernce.setNotifications(userdto.isNotifications());
	 userprefernce.setTheme(userdto.getTheme());
	 }
	 return user;
 }
}
