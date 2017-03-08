package com.quantal.clickbait.services.businessfacades;

import com.quantal.clickbait.dto.UserDTO;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.objectmapper.OrikaBeanMapper;
import com.quantal.clickbait.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.quantal.clickbait.services.workers.interfaces.LoginService;
/**
 * Created by dman on 11/10/2016.
 */

@Component
public class LoginFacade extends AbstractBaseFacade{


  private LoginService loginService;

  /*@Autowired
  private OrikaBeanMapper mapper; */

  @Autowired
  public  LoginFacade (LoginService loginService){
    this.loginService = loginService;
  }



  public ResponseEntity<?> loginUser(String email, String password) {


    User user = new User();
    UserDTO userDTO =  mapper.map(user, UserDTO.class);
    //ResponseEntity<ResponseDTO<UserDTO>> response = new ResponseEntity<ResponseDTO<UserDTO>>(uvm, ,);
    return toRESTResponse(userDTO);
  }
}
