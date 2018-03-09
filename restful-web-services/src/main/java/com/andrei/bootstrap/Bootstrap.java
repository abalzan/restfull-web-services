package com.andrei.bootstrap;

import java.time.LocalDate;

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
		
		userRepository.save(new User("John", LocalDate.now().minusDays(300),"password"));
		postRepository.save(new Post("Description 1", userRepository.findByName("John").get()));
		postRepository.save(new Post("Description 2", userRepository.findByName("John").get()));
		
		userRepository.save(new User("Jack", LocalDate.now().minusDays(300),"password"));
		postRepository.save(new Post("Description 3", userRepository.findByName("Jack").get()));
		postRepository.save(new Post("Description 4", userRepository.findByName("Jack").get()));
		
		userRepository.save(new User("Eve", LocalDate.now().minusDays(300), "password"));
		postRepository.save(new Post("Description 3", userRepository.findByName("Eve").get()));
		postRepository.save(new Post("Description 4", userRepository.findByName("Eve").get()));
		
	}
}
