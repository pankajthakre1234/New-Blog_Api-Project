package com.BikkadIT.blog.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.dto.UserDto;
import com.BikkadIT.blog.entity.Role;
import com.BikkadIT.blog.entity.User;
import com.BikkadIT.blog.exception.ResourceNotFoundException;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.repository.RoleRepository;
import com.BikkadIT.blog.repository.UserRepository;
import com.BikkadIT.blog.service.UserServicei;

@Service
public class UserServiceImpl implements UserServicei {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository repository;
	
	@Value("$ user.profile.image.path")
	private String imagePath;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * @author pankaj
	 * @apiNote This process is implementing  for save the user details
	 * 
	 */
	@Override
	public UserDto createUser(UserDto userDto) 
	{
		logger.info("Initiating dao call for save the User details");
		User user = this.mapper.map(userDto, User.class);

		User saveUser = this.repository.save(user);

		UserDto userDto2 = this.mapper.map(saveUser, UserDto.class);
		logger.info("Completed dao call for save the User details");
		return userDto2;
	}

	/**
	 * @apiNote This process is implementing for update the User details
	 * @param userId
	 */
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId)
	{
		logger.info("Initiating dao call for update the User details with id:{}",userId);
		User user = this.repository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		
		user.setName(userDto.getEmail());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		UserDto userDto2 = this.mapper.map(user, UserDto.class);
		logger.info("Completed dao call for update the User details with id:{}",userId);
		return userDto2;
	}

	/**
	 * @apiNote This process is implementing for get Single User details
	 * @param userId
	 */
	@Override
	public UserDto getUserById(Integer userId) 
	{
		logger.info("Initiating dao call for get the User details with id:{}",userId);
		User newUser = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		UserDto userDto = this.mapper.map(newUser, UserDto.class);
		logger.info("Completed dao call for get the User details with id:{}",userId);
		return userDto;
	}
	
	/**
	 * @apiNote This process is implementing for delete the User details
	 * @param userId
	 */
	@Override
	public void deleteUserById( Integer userId)
	{
		logger.info("Initiating dao call for delete the User details with id:{}",userId);
		User orElseThrow = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		logger.info("Completed dao call for delete the User details with id:{}",userId);

	}

	/**
	 * @apiNote This process is implementing for getAll the User details
	 */
	@Override
	public List<UserDto> getAllUser()
	{
		logger.info("Initiating dao call for get All the User details");
		List<User> allUsers = this.repository.findAll();

	 List<UserDto> list = allUsers.stream().map((users) -> mapper.map(users, UserDto.class))
				.collect(Collectors.toList());
		logger.info("Completed dao call for get All the User details");

		return list;
	}
	
	/**
	 * @apiNote  This Process Implementing the RagisterNew User
	 */

	@Override
	public UserDto registerNewUser(UserDto userDto)
	{
		logger.info("Initiating dao call for get the New Ragister User details");

		User user = this.mapper.map(userDto, User.class);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepository.findById(AppConstant.NORMAL_USER).get();
		
		user.getRoles().add(role);
		
		User newUser = this.repository.save(user);
	
		UserDto userDto2 = this.mapper.map(newUser,UserDto.class);
		
		logger.info("Completed dao call for get the New Ragister User details");

		return userDto2;
	}

}
