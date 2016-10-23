package com.quantal.clickbait.services;

import com.quantal.clickbait.exceptions.InvalidIdException;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.workers.interfaces.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by dman on 13/09/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private UserService userService;
  @Before
  public void setUp(){

  }

  @Test
  public void whenGivenUserIdReturnFoundUser() {

    Long userId = 1L;
    User foundUser = new User();
    foundUser.setId(userId);
    when(userService.findById(userId)).thenReturn(foundUser);
    User returnedUser = userService.findById(userId);

    assertEquals(userId, returnedUser.getId() );
    verify(userService).findById(userId);
  }

  @Test
  public void whenUserIdLessThan1ThrowInvalidIdExcepion(){
    long userId = 1;
    String errMsg = "Invalid Id";
    thrown.expect(InvalidIdException.class);
    thrown.expectMessage(errMsg);
    when(userService.findById(userId)).thenThrow(new InvalidIdException(errMsg));

    userService.findById(userId);
    verify(userService).findById(userId);
  }


}
