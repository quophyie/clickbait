package com.quantal.clickbait.services.workers.interfaces;

import com.quantal.clickbait.entities.User;

import java.util.List;

/**
 * Created by dman on 13/09/2016.
 */
public interface UserService {

  List<User> findAllUsers();
  User findById(long userId);
  User add(User userToCreate);
  User update(User user);
}
