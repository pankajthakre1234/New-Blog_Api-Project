package com.BikkadIT.blog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer postId;
	
	@NotEmpty(message = "Title must Not be Empty")
	@Size(min = 100, max = 2000)
	private String title;

	@NotEmpty(message = "Content must Not be Empty")
	@Size(min = 10, max = 1000)
	private String content;

	@NotBlank
	private String imageName;

	private Date addedDate;

	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();
}
