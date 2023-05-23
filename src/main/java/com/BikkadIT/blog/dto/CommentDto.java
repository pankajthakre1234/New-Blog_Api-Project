package com.BikkadIT.blog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	@NotEmpty
	private Integer id;
	
	@NotEmpty(message = "Content must not be empty")
	@Size(min = 100, max = 1000)
	private String content;
	
}
