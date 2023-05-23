package com.BikkadIT.blog.service;

import java.util.List;

import com.BikkadIT.blog.dto.PostDto;
import com.BikkadIT.blog.dto.PostResponse;
import com.BikkadIT.blog.entity.Post;

public interface PostServicei {

	PostDto createPost(PostDto postDto, Integer userId,Integer catId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost (Integer postId);
	
	PostDto getSinglePost(Integer postId);
	
	List<PostDto> getAllPosts();
	
	List<PostDto> getPostByCategory(Integer catId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	//List<PostDto> searchPost(String Keywords);
	
	List<PostDto> gellAllPost(Integer pageNumber,Integer pageSize);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
}
