package com.BikkadIT.blog.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BikkadIT.blog.dto.PostDto;
import com.BikkadIT.blog.dto.PostResponse;
import com.BikkadIT.blog.entity.Category;
import com.BikkadIT.blog.entity.Post;
import com.BikkadIT.blog.entity.User;
import com.BikkadIT.blog.exception.ResourceNotFoundException;
import com.BikkadIT.blog.repository.CategoryRepository;
import com.BikkadIT.blog.repository.PostRepository;
import com.BikkadIT.blog.repository.UserRepository;
import com.BikkadIT.blog.service.PostServicei;

@Service
public class PostServiceImpl implements PostServicei {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository catRepository;
	
	Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);

	/**@author pankaj
	 * @apiNote This process is implementing for save the Post details
	 * @param userId 
	 * @param catId
	 */
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId)
	{
		logger.info("Initiating dao call for the save the Post Details");
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Category category = this.catRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));

		Post post = this.mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepository.save(post);
		PostDto newDto = this.mapper.map(newPost, PostDto.class);
		logger.info("Completed dao call for the save the Post Details");

		return newDto;
	}
	
	/**
	 * @apiNote This process is implementing for update the Post details
	 * @param postId
	 */

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId)
	{
		logger.info("Initiating dao call for the update the Post Details with id:{}",postId);
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post savePost = this.postRepository.save(post);

		PostDto postDto2 = this.mapper.map(savePost, PostDto.class);
		logger.info("Completed dao call for the update the Post Details with id:{}",postId);
		return postDto2;
	}
	
	/**
	 * @apiNote This process is implementing for delete the User details
	 * @param postId
	 */

	@Override
	public void deletePost(Integer postId) 
	{
		logger.info("Initiating dao call for the delete the Post Details with id:{}",postId);
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		PostDto postDto = this.mapper.map(post, PostDto.class);
		logger.info("Completed dao call for the delete the Post Details with id:{}",postId);
		this.postRepository.delete(post);

	}
	
	/**
	 * @apiNote This process is implementing for get Single Post details
	 * @param postId
	 */

	@Override
	public PostDto getSinglePost(Integer postId) 
	{
		logger.info("Initiating dao call for get Single Post the Post Details with id:{}",postId);
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		PostDto postDto = this.mapper.map(post, PostDto.class);
		logger.info("Completed dao call for get Single Post the Post Details with id:{}",postId);

		return postDto;
	}

	/**
	 * @apiNote This process is implementing for get All the POst details
	 * 
	 */
	@Override
	public List<PostDto> getAllPosts()
	{
		logger.info("Initiating dao call for get all the Post Details");

		List<Post> findAll = this.postRepository.findAll();

		List<PostDto> postDtos = findAll.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("Completed dao call for get all the Post Details");

		return postDtos;
	}
	
	/**
	 * @apiNote This process is implementing for get POst by Category the Post details
	 * @param catId
	 */

	@Override
	public List<PostDto> getPostByCategory(Integer catId)
	{
		logger.info("Initiating dao call for get Post By Category Details with id:{}",catId);
		Category category = this.catRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));

		List<Post> posts = this.postRepository.findByCategory(category);

		List<PostDto> postDtos = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("Completed dao call for get Post By Category Details with id:{}",catId);
		return postDtos;
	}

	/**
	 * @apiNote This process is implementing for get Post By  the User details
	 * @param userId
	 */
	@Override
	public List<PostDto> getPostByUser(Integer userId) 
	{
		logger.info("Initiating dao call for get Post By User Details with id:{}",userId);
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<Post> posts = this.postRepository.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("Completed dao call for get Post By User Details with id:{}",userId);
		return postDtos;
	}

//	@Override
//	public List<PostDto> searchPost(String Keywords) 
//	{
//		logger.info("Initiating dao call for search Post By keywords Details");
//		List<Post> posts = this.postRepository.searchByTitle(Keywords);
//		List<PostDto> allpost = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
//		logger.info("Completed dao call for search Post By keywords Details");
//		return allpost;
//	}

	/**
	 * @apiNote This process is implementing for get All the Post details
	 */
	@Override
	public List<PostDto> gellAllPost(Integer pageNumber, Integer pageSize)
	{
		logger.info("Initiating dao call for get All Post Details");
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> page = this.postRepository.findAll(p);
		List<Post> findAll = page.getContent();

		List<PostDto> postDtos = findAll.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("Completed dao call for get All Post Details");
		return postDtos;
	}
	
	
	/**
	 * @apiNote This process is implementing for get All the Post details
	 */
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir)
	{
		logger.info("Initiating dao call for get All Post Details");
		Sort sort = null;
		if (sortDir.equals("asc")) {

			sort = Sort.by(sortBy).ascending();
		} else {

			sort = Sort.by(sortBy).descending();
		}

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = this.postRepository.findAll(p);
		List<Post> findAll = page.getContent();

		List<PostDto> postDtos = findAll.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse post = new PostResponse();

		post.setContent(postDtos);
		post.setPageNumber(page.getNumber());
		post.setPafgeSize(page.getSize());
		post.setTotalElements(page.getTotalElements());
		post.setTotalPages(page.getTotalPages());
		post.setLastPage(page.isLast());
		logger.info("Completed dao call for get All Post Details");
		return post;
	}

}
