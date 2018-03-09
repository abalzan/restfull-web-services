package com.andrei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrei.entity.Post;
import com.andrei.entity.User;
import com.andrei.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	
	public void save(User user, Post post) {
		post.setUser(user);
		postRepository.save(post);
	}

	
}
