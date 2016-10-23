package com.quantal.clickbait.controllers;

import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.businessfacades.LoginFacade;
import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dman on 10/10/2016.
 */
@Controller
@RequestMapping(path= "/login")
public class LoginController {

  @Autowired
  private LoginFacade loginFacade;

  public LoginController(LoginFacade loginFacade){
    this.loginFacade = loginFacade;
  }

  @PostMapping(path="/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> login(User user){

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);

    ResponseDTO<UserDTO> resVm = loginFacade.loginUser(user.getEmail(), user.getPassword());;

    ResponseEntity<ResponseDTO<UserDTO>> response = new ResponseEntity<>(resVm, headers, HttpStatus.OK);

    return response;
  }
}
