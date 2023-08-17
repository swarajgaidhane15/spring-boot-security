package com.demo.springsecurityclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springsecurityclient.model.UserModel;
import com.demo.springsecurityclient.service.UserService;

@RestController
public class RegistrationController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public String registerUser(@RequestBody UserModel userModel) {
    userService.registerUser(userModel);
    return "Success";
  }
}
