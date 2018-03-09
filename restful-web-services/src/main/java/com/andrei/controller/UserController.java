package com.andrei.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andrei.entity.Post;
import com.andrei.entity.User;
import com.andrei.service.PostService;
import com.andrei.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<User> retrieveAllUsers(){
		return userService.findAllUsers();
	}
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveAllUsersFiltering(){
		return userService.findAllUsersFiltering();
	}
	
	
	@GetMapping("/{id}")
	public Resource<User> retrieveUser(@PathVariable String id){
		User user = userService.findUserById(Long.parseLong(id));
		
		//HATEOAS add a link to get all users.
		Resource<User> resource = new Resource<User>(user);

		//here we are adding a link for all-users, calling the method retrieveAllUsers(),

		//		"_links": {
		//	        "all-users": {
		//	            "href": "http://localhost:8080/users"
		//	        }
		//	    }
		resource.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));
		
		return resource ;
	}
	
	
	@GetMapping("/{userId}/posts")
	public List<Post> retrievePostFromUser(@PathVariable String userId){
		return userService.findUserById(Long.parseLong(userId)).getPosts();
	}	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveUser(@Valid @RequestBody User user) {
		userService.save(user);
	}
	
	//User creating a post
	@PostMapping("/{userId}/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public void savePostFromUser(@PathVariable String userId, @RequestBody Post post) {
		User user = userService.findUserById(Long.parseLong(userId));
		
		postService.save(user, post);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable String id){
		userService.deleteUserById(Long.parseLong(id));
	}
	
}
