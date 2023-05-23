package com.BikkadIT.blog.dto;

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
public class CategoryDto {

	private Integer catId;

	@NotEmpty
	@Size(min = 100,max = 10000,message = "Title should be Proper and Must Not be Empty")
	private String catTitle;

	@NotEmpty(message = "Description may Be Proper and Must Not Empty")
	private String catDescription;
}
