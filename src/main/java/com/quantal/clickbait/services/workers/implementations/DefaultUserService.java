package com.quantal.clickbait.services.workers.implementations;

import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.repositories.interfaces.UserRepository;
import com.quantal.clickbait.services.workers.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dman on 13/09/2016.
 */
@Service
public class DefaultUserService implements UserService {


  private UserRepository userRepository;

  @Autowired
  public DefaultUserService(UserRepository userRepo){
    this.userRepository = userRepo;
  }

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findById(long userId) {
    return (User)userRepository.findSingle(userId);
  }

  @Override
  public User add(User userToCreate) {
     return (User)userRepository.create(userToCreate);
  }

  @Override
  public User update(User user) {
    return (User)userRepository.updateById(user);
  }
}
