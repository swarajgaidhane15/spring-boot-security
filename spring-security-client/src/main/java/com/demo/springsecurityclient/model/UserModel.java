package com.demo.springsecurityclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserModel {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String matchingPassword;
}
