package com.BikkadIT.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIT.blog.dto.PostDto;
import com.BikkadIT.blog.dto.PostResponse;
import com.BikkadIT.blog.helper.ApiResponce;
import com.BikkadIT.blog.helper.AppConstant;
import com.BikkadIT.blog.service.FileService;
import com.BikkadIT.blog.service.PostServicei;


@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServicei servicei;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	

	Logger logger = LoggerFactory.getLogger(PostController.class);
	

	/**
	 * @author pankaj
	 * @apiNote This api is use for Create the Post
	 * @param postDto
	 * @param userId
	 * @param catId
	 * @return
	 */
	// create post
	@PostMapping("/user/{userId}/category/{catId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer catId) {
		
		logger.info("Initiated request for save the Post details");
		PostDto createPost = this.servicei.createPost(postDto, userId, catId);
		logger.info("Completed request for save the Post details");
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	/**
	 * @apiNote This api is use for getAll Post By User
	 * @param userId
	 * @return
	 */
	// get post by User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllpostByUser(@PathVariable Integer userId)
	{
		
		logger.info("Initiated request for getAll Post details with userId:{}", userId);
		List<PostDto> allPosts = this.servicei.getPostByUser(userId);
		logger.info("Completed request for getAll Post detailswith userId:{}", userId);
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for getAll Post By Category
	 * @param catId
	 * @return
	 */
	// get post by Category
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getAllpostByCategory(@PathVariable Integer catId)
	{
		
		logger.info("Initiated request for getAll Post By Catregory details with catId:{}", catId);
		List<PostDto> allPosts = this.servicei.getPostByCategory(catId);
		logger.info("Completed request for getAll POst By Category details with catId:{}", catId);
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for getAll Posts
	 * @return
	 */
	// get all post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts()
	{
		logger.info("Initiated request for getAll Post details");
		List<PostDto> allPosts = this.servicei.getAllPosts();
		logger.info("Completed request for getAll Post details");
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for get Single Post
	 * @param postId
	 * @return
	 */
	// get post bY Id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getSinglepostById(@PathVariable Integer postId) 
	{
		logger.info("Initiated request for get Single Post details with postId:{}", postId);
		PostDto post = this.servicei.getSinglePost(postId);
		logger.info("Completed request for get Single Post details with postId:{}", postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for delete the Post
	 * @param postId
	 * @return
	 */
	// delete post by id
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponce> deletePostById(@PathVariable Integer postId) 
	{
		logger.info("Initiated request for deleted the Post details with postId:{}", postId);
		this.servicei.deletePost(postId);
		logger.info("Completed request for deleted the Post details with postId:{}", postId);
		return new ResponseEntity(new ApiResponce(AppConstant.POST_DELETE, false), HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for update the Post
	 * @param postDto
	 * @param postId
	 * @return
	 */
	// update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		logger.info("Initiated request for update the Post details with postId:{}", postId);
		PostDto updatePost = this.servicei.updatePost(postDto, postId);
		logger.info("Completed request for update the Post details with postId:{}", postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	/**
	 * @apiNote This api Is use for getAllPost By Pagination
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	// get all post by pagination
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPostbyPagging(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize) 
	{
		logger.info("Initiated request for get All Post By using Pagination details");
		List<PostDto> allPostByPagging = this.servicei.gellAllPost(pageNumber, pageSize);
		logger.info("Completed request for get All Post By using Pagination details");
		return new ResponseEntity<List<PostDto>>(allPostByPagging, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for getAllPost By using PageNumber,PageSize And
	 *          Sorting
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */
	// get all post by postResponse
	@GetMapping("/allpost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "dirBY", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir)
	{
		logger.info("Initiated request for get All Posts details");
		PostResponse allPost = this.servicei.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		logger.info("Completed request for get All Posts By details");
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	/**
	 * @apiNote This api is use for search by Title
	 * @param keywords
	 * @return
	 * @throws IOException
	 */
	// get post by Title
//	@GetMapping("/post/search/{keywords}")
//	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
//	{
//		logger.info("Initiated request for search Post by Title with details");
//		List<PostDto> searchPost = this.servicei.searchPost(keywords);
//		logger.info("Completed request for search Post by Title with details");
//		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
//	}

	/**
	 * @apiNote This api is use for the Post(upload) the Images  
	 * @param image
	 * @param postId
	 * @return
	 * @throws IOException
	 */
	//upload image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException
	{
		logger.info("Initiated request for upload the images for Posts details");
		String fileName = this.fileService.uploadImage(path, image);
		PostDto postDto = this.servicei.getSinglePost(postId);
		postDto.setImageName(fileName);
		PostDto updatePost = this.servicei.updatePost(postDto, postId);
		logger.info("Completed request for upload the images for Posts details");
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	
	/**
	 * @apiNote This api Is used for the get the image 
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */
	//method to serve files
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
	{
		logger.info("Initiated request for get and Serve the images for Posts details");
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		logger.info("Completed request for get and Serve the images for Posts details");
	}

}
