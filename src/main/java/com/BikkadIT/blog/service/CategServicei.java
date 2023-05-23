package com.BikkadIT.blog.service;

import java.util.List;

import com.BikkadIT.blog.dto.CategoryDto;
import com.BikkadIT.blog.entity.Category;

public interface CategServicei {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
	
	void deleteCategory(Integer catId);
	
	CategoryDto getSingleCategory(Integer catId);
	
	List<CategoryDto> getAllCategory();
}
