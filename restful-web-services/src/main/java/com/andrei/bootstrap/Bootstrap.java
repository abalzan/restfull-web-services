package com.andrei.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.andrei.entity.Post;
import com.andrei.entity.User;
import com.andrei.repository.PostRepository;
import com.andrei.repository.UserRepository;

@Component
public class Bootstrap implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		addUsers();
	}

	private void addUsers() {
		List<Post> posts = new ArrayList<>();
		posts.add(new Post("Description 1"));
		posts.add(new Post("Description 2"));
		userRepository.save(new User("John", LocalDate.now().minusDays(300),"password", posts));
		
		posts = new ArrayList<>();
		posts.add(new Post("Description 3"));
		posts.add(new Post("Description 4"));
		userRepository.save(new User("Jack", LocalDate.now().minusDays(300),"password", posts));
		
		posts = new ArrayList<>();
		posts.add(new Post("Description 5"));
		posts.add(new Post("Description 6"));
		userRepository.save(new User("Eve", LocalDate.now().minusDays(300), "password", posts));
	}
}
