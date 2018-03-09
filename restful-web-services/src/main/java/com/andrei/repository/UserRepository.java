package com.andrei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrei.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByName(String name);

}
