package com.quantal.clickbait.services.workers.interfaces;

import com.quantal.clickbait.entities.User;

/**
 * Created by dman on 11/10/2016.
 */
public interface LoginService {
  User loginUser(String email, String password);

  boolean logOutUser(User user);
}
