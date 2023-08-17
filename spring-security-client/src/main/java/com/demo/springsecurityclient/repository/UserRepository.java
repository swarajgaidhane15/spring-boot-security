package com.demo.springsecurityclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springsecurityclient.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
