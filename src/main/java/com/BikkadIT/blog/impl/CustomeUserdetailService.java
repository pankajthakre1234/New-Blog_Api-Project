package com.BikkadIT.blog.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.entity.User;
import com.BikkadIT.blog.exception.ResourceNotFoundException;
import com.BikkadIT.blog.repository.UserRepository;

@Service
public class CustomeUserdetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @author Pankaj
	 * @apiNote
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// loading from database by username

		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email " + username, 10));

		return user;
	}

}
