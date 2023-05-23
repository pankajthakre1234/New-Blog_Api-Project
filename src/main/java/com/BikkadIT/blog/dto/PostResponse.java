package com.BikkadIT.blog.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PostResponse {

	@NotEmpty(message = "Content Must Not Be Empty")
	@Size(min = 10,max = 1000)
	private List<PostDto> content;
	
	@NotBlank
	private Integer pageNumber;
	
	@NotBlank
	private Integer pafgeSize;
	
	@NotEmpty
	private Long totalElements;
	
	@NotBlank
	private Integer totalPages;
	
	private boolean lastPage;
}
