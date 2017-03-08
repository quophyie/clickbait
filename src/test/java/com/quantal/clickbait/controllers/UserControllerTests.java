package com.quantal.clickbait.controllers;

import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.dto.UserDTO;
import com.quantal.clickbait.matchers.ClickbaitMatchers;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.businessfacades.UserFacade;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

  @Mock
  private UserFacade userFacade = new UserFacade(userService);

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
    UserDTO userToCreate = new UserDTO();
    userToCreate.setFirstName(firstName);
    userToCreate.setLastName(lastName);
    userToCreate.setEmail(email);
    userToCreate.setPassword(password);

    UserDTO  createdUser = new UserDTO();
    createdUser.setId(userId);
    createdUser.setFirstName(firstName);
    createdUser.setLastName(lastName);
    createdUser.setEmail(email);
    createdUser.setPassword(password);


    ResponseEntity response = new ResponseEntity(new ResponseDTO<>("OK", 100, createdUser), HttpStatus.OK);
    ClickbaitMatchers.UserDTOMatcher userMatcher = new ClickbaitMatchers.UserDTOMatcher(userToCreate);
    when(userFacade.createUser(Matchers.argThat(userMatcher))).thenReturn(response);



    mockMvc.perform(post("/users/")
          .content(TestUtil.convertObjectToJsonString(userToCreate))
          .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.data.firstName").value(firstName))
        .andExpect(jsonPath("$.data.id").value((int)userId));

    verify(userFacade).createUser(Matchers.argThat(userMatcher));
  }

  @Test
  public void shouldReturnUserWhenGivenId() throws Exception{

    UserDTO user = new UserDTO();
    user.setId(userId);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);

    ResponseEntity response = new ResponseEntity(new ResponseDTO<>("OK", 100, user), HttpStatus.OK);

    when(userFacade.findUser(eq(userId))).thenReturn(response);

    mockMvc.perform(get("/users/{id}",userId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").value((int)userId))
        .andExpect(jsonPath("$.data.email").value(email));
    verify(userFacade).findUser(eq(userId));
  }

  @Test
  public void shouldUpdateUserGivenUpdateData() throws Exception{
    UserDTO  userUpdateData = new UserDTO();
    userUpdateData.setId(userId);
    userUpdateData.setFirstName("Tom");
    userUpdateData.setLastName("Brexit");
    userUpdateData.setEmail(email);
    userUpdateData.setPassword(password);

    ClickbaitMatchers.UserDTOMatcher userDTOMatcher = new ClickbaitMatchers.UserDTOMatcher(userUpdateData);
    ResponseEntity response = new ResponseEntity(new ResponseDTO<>("OK", 100, userUpdateData), HttpStatus.OK);

    when(userFacade.updateUser(eq(userId), Matchers.argThat(userDTOMatcher)))
        .thenReturn(response);
    mockMvc.perform(put("/users/{id}", userId)
        //.param("id", "1")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(TestUtil.convertObjectToJsonString(userUpdateData)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("OK"));

    verify(userFacade).updateUser(eq(userId), Matchers.argThat(userDTOMatcher));
  }

  @After()
  public void tearDown() {
    userService = null;
    mockMvc = null;

  }
}
