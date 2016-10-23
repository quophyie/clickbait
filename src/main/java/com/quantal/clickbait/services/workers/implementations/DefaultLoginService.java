package com.quantal.clickbait.services.workers.implementations;

import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.workers.interfaces.LoginService;
import org.springframework.stereotype.Service;

/**
 * Created by dman on 10/10/2016.
 */
@Service
public class DefaultLoginService implements LoginService {
  @Override
  public User loginUser(String email, String password) {
    return null;
  }

  @Override
  public boolean logOutUser(User user) {
    return false;
  }
}
