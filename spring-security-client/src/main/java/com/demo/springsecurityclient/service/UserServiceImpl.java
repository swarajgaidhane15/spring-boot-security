package com.demo.springsecurityclient.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springsecurityclient.entity.User;
import com.demo.springsecurityclient.entity.VerificationToken;
import com.demo.springsecurityclient.model.UserModel;
import com.demo.springsecurityclient.repository.UserRepository;
import com.demo.springsecurityclient.repository.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private VerificationTokenRepository verificationTokenRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(UserModel userModel) {
    User existingUser = userRepository.findByEmail(userModel.getEmail());
    if (existingUser != null) {
      return null;
    }

    User user = new User();
    user.setEmail(userModel.getEmail());
    user.setFirstName(userModel.getFirstName());
    user.setLastName(userModel.getLastName());
    user.setRole("USER");
    user.setPassword(passwordEncoder.encode(userModel.getPassword()));

    userRepository.save(user);
    return user;
  }

  @Override
  public void saveVerificationTokenForUser(String token, User user) {
    VerificationToken verificationToken = new VerificationToken(user, token);
    verificationTokenRepository.save(verificationToken);
  }

  @Override
  public String validateVerificationToken(String token) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
    if (verificationToken == null)
      return "invalid";

    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();

    if (verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
      verificationTokenRepository.delete(verificationToken);
      return "expired";
    }

    user.setEnabled(true);
    userRepository.save(user);
    return "valid";
  }
}
