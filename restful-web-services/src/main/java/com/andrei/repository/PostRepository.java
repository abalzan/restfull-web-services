package com.andrei.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrei.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
