package com.BikkadIT.blog.service;

import java.util.List;

import com.BikkadIT.blog.dto.UserDto;

public interface UserServicei {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	void deleteUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
	UserDto registerNewUser(UserDto userDto);

	
}
