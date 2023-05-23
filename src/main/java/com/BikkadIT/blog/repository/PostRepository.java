package com.BikkadIT.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.BikkadIT.blog.dto.PostDto;
import com.BikkadIT.blog.entity.Category;
import com.BikkadIT.blog.entity.Post;
import com.BikkadIT.blog.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
//	@Query("select p * from Post p where p.title like:key")
//	List<Post> searchByTitle(@Param("%key%")String title);
	
}
