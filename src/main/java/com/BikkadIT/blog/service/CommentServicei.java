package com.BikkadIT.blog.service;

import com.BikkadIT.blog.dto.CommentDto;
import com.BikkadIT.blog.entity.Comment;

public interface CommentServicei {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer postId);
}
