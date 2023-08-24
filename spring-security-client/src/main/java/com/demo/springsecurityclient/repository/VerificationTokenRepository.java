package com.demo.springsecurityclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springsecurityclient.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
  VerificationToken findByToken(String token);
}
