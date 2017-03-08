package com.quantal.clickbait.controllers;

import com.quantal.clickbait.dto.LoginDTO;
import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.businessfacades.LoginFacade;
import com.quantal.clickbait.services.workers.interfaces.LoginService;
import com.quantal.clickbait.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by dman on 10/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTests {


  @Mock
  private LoginService loginService;
  //@InjectMocks
  @Mock
  private LoginFacade loginFacade;
  @InjectMocks
  private LoginController loginController;

  private MockMvc mockMvc;

  private long userId = 1;
  private String password;
  private String email;
  private String firstName;
  private String lastName;

  private LoginDTO loginDTO;

  @Before()
  public void setUp(){
    //loginFacade = new LoginFacade(loginService);
    //loginController = new LoginController(loginFacade) ;
    mockMvc = standaloneSetup(loginController)
        .setMessageConverters(new MappingJackson2HttpMessageConverter())
        .build();

    loginDTO = new LoginDTO();
    userId = 1;
    password = "password";
    email = "email@quanta.com";
    firstName = "George";
    lastName = "Test";
  }

  @Test
  public void shouldReturnUserGivenCorrectUsernameAndPassword() throws Exception {
    String loginSuccessMsg = "Login Successful";
    loginDTO.setEmail(email);
    loginDTO.setPassword(password);
    when(loginFacade.loginUser(email, password)).thenReturn(new ResponseEntity(new ResponseDTO(loginSuccessMsg, 100,loginDTO), HttpStatus.OK));
    mockMvc.perform(post("/login/").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(TestUtil.convertObjectToJsonString(loginDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value(loginSuccessMsg));

    verify(loginFacade).loginUser(email, password);
  }
}
