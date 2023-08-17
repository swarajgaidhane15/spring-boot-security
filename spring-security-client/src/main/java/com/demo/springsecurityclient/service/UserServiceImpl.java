package com.demo.springsecurityclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springsecurityclient.entity.User;
import com.demo.springsecurityclient.model.UserModel;
import com.demo.springsecurityclient.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(UserModel userModel) {
    User user = new User();
    user.setEmail(userModel.getEmail());
    user.setFirstName(userModel.getFirstName());
    user.setLastName(userModel.getLastName());
    user.setRole("USER");
    user.setPassword(passwordEncoder.encode(userModel.getPassword()));

    userRepository.save(user);
    return user;
  }

}
