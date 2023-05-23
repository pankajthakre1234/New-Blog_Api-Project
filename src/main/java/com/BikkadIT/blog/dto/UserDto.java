package com.BikkadIT.blog.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private Integer id;

	@NotEmpty
	@Size(min = 4,max = 10,message = "Name Must Not Be Empty")
	private String name;

	@Email
	@Size(min = 10,message = "Email Id Not Valid, please use proper mailId")
	private String email;

	@NotEmpty
	@Size(min = 5, max = 10,message = "Password is first Char is Capital and Must Not be Empty")
	private String password;

	@NotEmpty
	@Size(min = 100, max = 10000)
	private String about;
}
