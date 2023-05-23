package com.BikkadIT.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BikkadIT.blog.entity.Role;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.repository.RoleRepository;

@SpringBootApplication
public class UserBlogAppApisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserBlogAppApisApplication.class, args);
		
		System.out.println("Blog App is Running.....!!!");
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository repository;

	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(passwordEncoder.encode("pankaj"));
		
		try {

			Role role = new Role();
			role.setRoleid(AppConstant.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setRoleid(AppConstant.NORMAL_USER);;
			role1.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);

			List<Role> result = this.repository.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}


}	

