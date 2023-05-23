package com.BikkadIT.blog.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.blog.dto.JwtAuthRequest;
import com.BikkadIT.blog.dto.UserDto;
import com.BikkadIT.blog.entity.User;
import com.BikkadIT.blog.exception.ApiException;
import com.BikkadIT.blog.impl.UserDetailService;
import com.BikkadIT.blog.impl.UserServiceImpl;
import com.BikkadIT.blog.repository.UserRepository;
import com.BikkadIT.blog.security.JWTTokenHelper;
import com.BikkadIT.blog.security.JwtAuthResponce;
import com.BikkadIT.blog.service.UserServicei;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServicei userServicei;

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	/**
	 * @author Pankaj
	 * @param authRequest
	 * @apiNote This api is used for the User login
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> createToken(@RequestBody JwtAuthRequest authRequest) {
		logger.info("Intiating the Request for The Save The Loging Details");
		this.authenticate(authRequest.getUsername(), authRequest.getPassword());

		UserDetails userDetails = this.userDetailService.loadUserByUsername(authRequest.getUsername());

		String generateToken = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponce response = new JwtAuthResponce();
		response.setToken(generateToken);

		logger.info("Complete the Request for The Save The Loging Details");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		this.authenticationManager.authenticate(authenticationToken);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}
	}
	// register new user api
	/**
	 * @apiNote This Api is used for the Rgister the User Details
	 * @param userDto
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto registeredUser = this.userServicei.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

	// get loggedin user data
	/**
	 * @apiNote This Api is used for the Getting the Current user Details
	 * @param principal
	 * @return
	 */
	@GetMapping("/current-user/")
	public ResponseEntity<UserDto> getUser(Principal principal) {
		User user = this.repository.findByEmail(principal.getName()).get();
		return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
	}

}
