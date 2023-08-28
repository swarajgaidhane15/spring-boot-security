package com.springsecurity.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.oauthserver.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}
