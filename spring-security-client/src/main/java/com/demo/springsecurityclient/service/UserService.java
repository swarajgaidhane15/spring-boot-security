package com.demo.springsecurityclient.service;

import java.util.Optional;

import com.demo.springsecurityclient.entity.User;
import com.demo.springsecurityclient.entity.VerificationToken;
import com.demo.springsecurityclient.model.UserModel;

public interface UserService {

  User registerUser(UserModel userModel);

  void saveVerificationTokenForUser(String token, User user);

  String validateVerificationToken(String token);

  VerificationToken generateNewVerificationToken(String oldToken);

  User findUserByEmail(String email);

  void createPasswordResetTokenForUser(User user, String token);

  String validatePasswordResetToken(String token);

  Optional<User> getUserByPasswordResetToken(String token);

  void changePassword(User user, String newPassword);

  boolean checkIfValidOldPassword(User user, String oldPassword);
}
