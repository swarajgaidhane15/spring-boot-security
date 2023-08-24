package com.demo.springsecurityclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springsecurityclient.entity.User;
import com.demo.springsecurityclient.event.RegistrationCompleteEvent;
import com.demo.springsecurityclient.model.UserModel;
import com.demo.springsecurityclient.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

  @Autowired
  private UserService userService;

  @Autowired
  private ApplicationEventPublisher publisher;

  @PostMapping("/register")
  public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
    User user = userService.registerUser(userModel);
    if (user == null)
      return "User already exists";

    publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
    return "Success";
  }

  @GetMapping("/verifyRegistration")
  public String verifyRegistration(@RequestParam("token") String token) {
    String result = userService.validateVerificationToken(token);
    if (result.equalsIgnoreCase("valid"))
      return "User Verified Successfully";
    return "Bad User";
  }

  private String applicationUrl(HttpServletRequest request) {
    return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
  }
}
