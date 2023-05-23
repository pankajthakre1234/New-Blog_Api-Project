package com.BikkadIT.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.blog.dto.CommentDto;
import com.BikkadIT.blog.helper.ApiResponce;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.service.CommentServicei;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentServicei commentServicei;
	
	Logger logger=LoggerFactory.getLogger(CommentController.class);
	
	/**
	 * @apiNote This Api Is use for Create the Comment
	 * @author Pankaj
	 * @param commentDto
	 * @param postId
	 * @return
	 */
	//create Comment
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable Integer postId)
	{
		logger.info("Initiated request for save the Comment details ");
		CommentDto createComment = this.commentServicei.createComment(commentDto, postId);
		logger.info("Completed request for save the Comment details");
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	/**
	 * @apiNote This Api is use for Delete the comment
	 * @param id
	 * @return
	 */
	// detele the comment
	@DeleteMapping("/comment/{Id}")
	public ResponseEntity<ApiResponce> deleteComment(@PathVariable Integer id)
	{
		logger.info("Intiated request for delete the Comment details with id:{}",id);
		this.commentServicei.deleteComment(id);
		logger.info("Completed request for delete the Comment details with id:{}",id);
		return new ResponseEntity(new ApiResponce(AppConstant.DELETE_SUCCESS,false),HttpStatus.OK);
	}
}
