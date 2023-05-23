package com.BikkadIT.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.blog.dto.UserDto;
import com.BikkadIT.blog.helper.ApiResponce;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.service.UserServicei;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

	@Autowired
	private UserServicei servicei;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	Logger logger=LoggerFactory.getLogger(UserController.class);

	/**
	 * @author Pankaj
	 * @apiNote This api is use for CreateUser
	 * @param userDto
	 * @return
	 */
	// create user
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) 
	{
		logger.info("Initiated request for save the User details");
		
		String encode = this.passwordEncoder.encode(userDto.getPassword());
		
		UserDto createUser = this.servicei.createUser(userDto);
		logger.info("Completed request for save the User details");
		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
	}

	/**
	 * @apiNote This api is use for update the User
	 * @param userDto
	 * @param id
	 * @return
	 */
	// update user
	@PutMapping("/userupdate/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id)
	{
		logger.info("Initiated request for update the User details with userId:{}",id);
		UserDto updateUser = this.servicei.updateUser(userDto, id);
		logger.info("Completed request for update the User details with userId:{}",id);
		return new ResponseEntity<>(updateUser, HttpStatus.ACCEPTED);
	}

	/**
	 * @apiNote This api is use for get user by Id
	 * @param id
	 * @return
	 */
	// get single user by id
	@GetMapping("/singleuser/{id}")
	public ResponseEntity<UserDto> getUserByid(@PathVariable Integer id)
	{
		logger.info("Initiated request for get Single User details with userId:{}",id);
		UserDto userById = this.servicei.getUserById(id);
		logger.info("Completed request for get Single User details with userId:{}",id);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for get All Users
	 * @return
	 */
	// get all users
	@GetMapping("/allusers")
	public ResponseEntity<List<UserDto>> getAllUser() 
	{
		logger.info("Initiated request for get All the User details");
		List<UserDto> allUser = this.servicei.getAllUser();
		logger.info("Completed request for get All the User details");
		return new ResponseEntity<List<UserDto>>(allUser, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for delete the User
	 * @param userId
	 * @return
	 */
	// delete users by id
	@DeleteMapping("/userDelete/{userId}")
	public ResponseEntity<ApiResponce> deleteUserByid(@PathVariable Integer userId)
	{
		logger.info("Initiated request for delete the User details with userId:{}",userId);
		this.servicei.deleteUserById(userId);
		logger.info("Completed request for delete the User details with userId:{}",userId);
		return new ResponseEntity(new ApiResponce(AppConstant.DELETE_SUCCESS, false), HttpStatus.OK);
	}
}
