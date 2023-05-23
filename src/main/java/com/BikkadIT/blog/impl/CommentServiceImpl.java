package com.BikkadIT.blog.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.dto.CommentDto;
import com.BikkadIT.blog.entity.Comment;
import com.BikkadIT.blog.entity.Post;
import com.BikkadIT.blog.exception.ResourceNotFoundException;
import com.BikkadIT.blog.repository.CommentRepository;
import com.BikkadIT.blog.repository.PostRepository;
import com.BikkadIT.blog.service.CommentServicei;

@Service
public class CommentServiceImpl implements CommentServicei {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ModelMapper modelMapper;

	Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	/**
	 * @author pankaj
	 * @apiNote This Process is Implementing for the create the Comment Details
	 * @param postId
	 */
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		logger.info("Initiating dao call for the save the Comment Details");
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);

		Comment savecomment = this.commentRepository.save(comment);
		logger.info("Completed dao call for the save the Comment Details");
		return this.modelMapper.map(savecomment, CommentDto.class);
	}

	/**
	 * @author pankaj
	 * @apiNote This Process is Implementing for the delete the Comment Details
	 */
	@Override
	public void deleteComment(Integer id) {
		logger.info("Initiating dao call for the delete the Comment Details");
		Comment comment = this.commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
		logger.info("Completed dao call for the delete the Comment Details");

		this.commentRepository.delete(comment);
	}

}
