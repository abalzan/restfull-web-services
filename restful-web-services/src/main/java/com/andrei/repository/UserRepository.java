package com.andrei.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrei.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
