package com.BikkadIT.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.blog.dto.CategoryDto;
import com.BikkadIT.blog.helper.ApiResponce;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.service.CategServicei;


@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategServicei servicei;
	
	
	Logger logger= LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * @author pankaj
	 * @param categoryDto
	 * @apiNote This api is use for Created the Category
	 * @return
	 */
	// create category
	@PostMapping("/category")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		logger.info("Initiated request for save the Category details");
		CategoryDto createCategory = this.servicei.createCategory(categoryDto);
		logger.info("Completed request for save the Category details");
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param categoryDto
	 * @param catId
	 * @apiNote This api is use for Update the Category
	 * @return
	 */
	//update category
	@PutMapping("/category/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
	{
		logger.info("Initiated request for Update the Category details with catId:{}",catId);
		CategoryDto updateCategory = this.servicei.updateCategory(categoryDto, catId);
		logger.info("Completed request for Update the Category details with catId:{}",catId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.ACCEPTED);
	}
	
	/**
	 * @apiNote This api  is use for delete the Category
	 * @param catId
	 * @return
	 */
	//delete category
	@DeleteMapping("/category/{catId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer catId)
	{
		logger.info("Initiated request for deleted the Category details with catId:{}",catId);
		this.servicei.deleteCategory(catId);
		logger.info("Completed request for deleted the Category details with catId:{}",catId);
		return new ResponseEntity(new ApiResponce(AppConstant.DELETE_SUCCESS, false),HttpStatus.OK);
	}
	
	/**
	 * @apiNote This api is use for get Single Category
	 * @param catId
	 * @return
	 */
	//getSingle category
	@GetMapping("/category/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		logger.info("Initiated request for Get the Category details with catId:{}",catId);
		CategoryDto category = this.servicei.getSingleCategory(catId);
		logger.info("Completed request for Get the Category details with catId:{}",catId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	/**
	 * @apiNote This api is use for getAll Categories
	 * @return
	 */
	//getAll category
	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getAllcategory()
	{
		logger.info("Initiated request for GetAll the Category details");
		List<CategoryDto> allCategory = this.servicei.getAllCategory();
		logger.info("Completed request for GetAll the Category details");
		return new ResponseEntity<List<CategoryDto>>(allCategory,HttpStatus.OK);
	}
}
