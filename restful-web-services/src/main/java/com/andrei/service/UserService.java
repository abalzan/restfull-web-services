package com.andrei.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.andrei.entity.User;
import com.andrei.exception.UserNotFoundException;
import com.andrei.repository.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers(){	
		return userRepository.findAll();
	}
	
	public MappingJacksonValue findAllUsersFiltering() {
		//Dynamic filtering-To make it works, you need to uncomment @JsonFilter of User Entity, 
		//but after you do that all other rest calls won't work.
		//TO implemment this filter you need to configure all the rest calls to return a MappingJacksonValue as the sample bellow.
		//when the user call this rest, the password won't be show. 
		MappingJacksonValue mapping = new MappingJacksonValue(userRepository.findAll());
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "birthDate", "posts");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("PasswordFilter", filter);
		mapping.setFilters(filters);
		
		return mapping; 
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public User findUserById(Long id) {

		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("No user found with id: " + id);
		}

		return optionalUser.get();
	}

	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("No user found with id: " + id);
		}

		userRepository.deleteById(id);
	}
}
