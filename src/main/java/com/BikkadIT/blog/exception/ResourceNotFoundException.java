package com.BikkadIT.blog.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;

	private String feildName;

	private Integer feildValue;

	public ResourceNotFoundException(String resourceName, String feildName, Integer feildValue) {
		super(String.format("%s Not Found with Id %s : %s", resourceName, feildName, feildValue));
		this.resourceName = resourceName;
		this.feildName = feildName;
		this.feildValue = feildValue;
	}
}
