package com.quantal.clickbait.services.businessfacades;

import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.dto.UserDTO;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.workers.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by dman on 23/10/2016.
 */
@Component
public class UserFacade extends AbstractBaseFacade{


  private UserService userService;
  @Autowired
  public UserFacade(UserService userServ){
    this.userService = userServ;
  }

  public ResponseEntity<?> createUser(UserDTO userToCreate) {
    User createUserData = mapper.map(userToCreate, User.class);
    createUserData.setId(null);
    User createdUser = userService.add(createUserData);
    UserDTO createdUserDTO = mapper.map(createdUser, UserDTO.class);
    ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>(createdUserDTO);
    return toRESTResponse(responseDTO, HttpStatus.OK,null);
  }
}
