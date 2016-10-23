package com.quantal.clickbait.services.businessfacades;

import com.quantal.clickbait.dto.UserDTO;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.objectmapper.OrikaBeanMapper;
import com.quantal.clickbait.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.quantal.clickbait.services.workers.interfaces.LoginService;
/**
 * Created by dman on 11/10/2016.
 */

@Component
public class LoginFacade {


  private LoginService loginService;

  @Autowired
  private OrikaBeanMapper mapper;

  @Autowired
  public  LoginFacade (LoginService loginService){
    this.loginService = loginService;
  }



  public ResponseDTO<UserDTO> loginUser(String email, String password) {


    User user = new User();
    user.setPassword("VerifiedPassword");
    user.setEmail("myemail@thecompany.com");

    UserDTO uvm =  mapper.map(user, UserDTO.class);
    ResponseDTO<UserDTO> response = new ResponseDTO<>("OK", 278, uvm);
    return response;
  }
}
