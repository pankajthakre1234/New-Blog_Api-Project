package com.BikkadIT.blog.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.dto.CategoryDto;
import com.BikkadIT.blog.entity.Category;
import com.BikkadIT.blog.exception.ResourceNotFoundException;
import com.BikkadIT.blog.repository.CategoryRepository;
import com.BikkadIT.blog.service.CategServicei;

@Service
public class CategoryServiceImpl implements CategServicei {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private ModelMapper mapper;
	
	Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

	/**
	 * @author pankaj
	 * @apiNote This Process is Implementing for the create the Category Details
	 */
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		logger.info("Initiating dao call for the save the Category Details");
		Category category = this.mapper.map(categoryDto, Category.class);

		Category saveCategory = this.repository.save(category);

		CategoryDto categoryDto2 = this.mapper.map(saveCategory, CategoryDto.class);
		logger.info("Completed dao call for the save the Category Details");
		return categoryDto2;
	}

	/**
	 * @apiNote This Process is Implementing for the update the Category Details
	 */
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) 
	{
		logger.info("Initiating dao call for the update the Category Details with id:{}",catId);
		Category cat = this.repository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));

		cat.setCatTitle(categoryDto.getCatTitle());
		cat.setCatDescription(categoryDto.getCatDescription());

		CategoryDto catDto = this.mapper.map(cat, CategoryDto.class);
		logger.info("Completed dao call for the update the Category Details with id:{}",catId);
		return catDto;
	}

	/**
	 * @apiNote This Process is Implementing for the delete the Category Details
	 */
	@Override
	public void deleteCategory(Integer catId)
	{
		logger.info("Initiating dao call for the delete the Category Details with id:{}",catId);
		Category category = this.repository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));

		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);
		logger.info("Completed dao call for the delete the Category Details with id:{}",catId);
		this.repository.delete(category);
	}

	/**
	 * @apiNote This Process is Implementing for the get Single  the Category Details
	 */
	@Override
	public CategoryDto getSingleCategory(Integer catId)
	{
		logger.info("Initiating dao call for the get Single Category the Category Details with id:{}",catId);
		Category category = this.repository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));

		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);
		logger.info("Completed dao call for the get Single Category the Category Details with id:{}",catId);
		return categoryDto;
	}

	/**
	 * @apiNote This Process is Implementing for the get All the Category Details
	 */
	@Override
	public List<CategoryDto> getAllCategory()
	{
		logger.info("Initiating dao call for the get All Category the Category Details");

		List<Category> allList = this.repository.findAll();

		List<CategoryDto> catList = allList.stream().map((category) -> this.mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		logger.info("Completed dao call for the get All Category the Category Details");

		return catList;
	}

}