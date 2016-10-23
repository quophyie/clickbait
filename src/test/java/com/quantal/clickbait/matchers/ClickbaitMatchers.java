package com.quantal.clickbait.matchers;

import com.quantal.clickbait.entities.User;
import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

/**
 * Created by dman on 14/09/2016.
 */
public class ClickbaitMatchers {
  public static class UserMatcher extends ArgumentMatcher<User> {

    private final User expected;

    public UserMatcher(User expected) {
      this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
      // could improve with null checks
      return ((User) actual).getEmail().equalsIgnoreCase(expected.getEmail()) ||
          ((User) actual).getId() == expected.getId();
    }

    @Override
    public void describeTo(Description description) {
      description.appendText(expected == null ? null : expected.toString());
    }

  }
}
