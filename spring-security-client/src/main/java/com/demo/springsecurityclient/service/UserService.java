package com.demo.springsecurityclient.service;

import com.demo.springsecurityclient.entity.User;
import com.demo.springsecurityclient.model.UserModel;

public interface UserService {

  User registerUser(UserModel userModel);

}
