package com.demo.springsecurityclient.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.demo.springsecurityclient.entity.User;
import com.demo.springsecurityclient.event.RegistrationCompleteEvent;
import com.demo.springsecurityclient.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(RegistrationCompleteEvent event) {
    // Create the verification token for the user with link
    User user = event.getUser();
    String token = UUID.randomUUID().toString();
    userService.saveVerificationTokenForUser(token, user);

    // Send mail to user
    String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
    log.info("Click the link to verify your account: {}", url);
  }

}
