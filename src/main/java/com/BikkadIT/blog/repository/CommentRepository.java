package com.BikkadIT.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.blog.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
