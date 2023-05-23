package com.BikkadIT.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.blog.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
