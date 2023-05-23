package com.BikkadIT.blog.impl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.entity.User;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(AppConstant.EMAIL_NOT_FOUND));

		return user;
	}

}
