package com.quantal.clickbait.controllers;

import com.quantal.clickbait.matchers.ClickbaitMatchers;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.workers.interfaces.UserService;
import com.quantal.clickbait.util.TestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private long userId = 1;
  private String password;
  private String email;
  private String firstName;
  private String lastName;


  @Before
  public void setUp(){
    mockMvc = standaloneSetup(userController)
        .setMessageConverters(new MappingJackson2HttpMessageConverter())
        .build();

    userId = 1;
    password = "password";
    email = "email@quanta.com";
    firstName = "George";
    lastName = "Test";

  }

  @Test
  public void whenGivenUserPostDataCreateNewUser() throws Exception {
    User userToCreate = new User();
    userToCreate.setFirstName(firstName);
    userToCreate.setLastName(lastName);
    userToCreate.setEmail(email);
    userToCreate.setPassword(password);

    User  createdUser = new User();
    createdUser.setId(userId);
    createdUser.setFirstName(firstName);
    createdUser.setLastName(lastName);
    createdUser.setEmail(email);
    createdUser.setPassword(password);

    when(userService.add(Matchers.argThat(new ClickbaitMatchers.UserMatcher(userToCreate)))).thenReturn(createdUser);

    mockMvc.perform(post("/users/")
          .content(TestUtil.convertObjectToJsonString(userToCreate))
          .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.firstName").value(firstName))
        .andExpect(jsonPath("$.id").value((int)userId));

    verify(userService).add(Matchers.any(User.class));
  }

  @Test
  public void shouldReturnUserWhenGivenId() throws Exception{
    User user = new User();
    user.setId(userId);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);

    when(userService.findById(userId)).thenReturn(user);

    mockMvc.perform(get("/users/").param("id", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value((int)userId))
        .andExpect(jsonPath("$.email").value(email));
    verify(userService).findById(userId);
  }

  @Test
  public void shouldUpdateUserGivenUpdateData() throws Exception{
    User  userUpdateData = new User();
    userUpdateData.setId(userId);
    userUpdateData.setFirstName("Tom");
    userUpdateData.setLastName("Brexit");
    userUpdateData.setEmail(email);
    userUpdateData.setPassword(password);

    mockMvc.perform(put("/users/")
        .param("id", "1")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(TestUtil.convertObjectToJsonString(userUpdateData)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("OK"));
  }

  @After()
  public void tearDown() {
    userService = null;
    mockMvc = null;

  }
}
