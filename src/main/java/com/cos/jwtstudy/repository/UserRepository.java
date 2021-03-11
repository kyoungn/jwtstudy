package com.cos.jwtstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.jwtstudy.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
