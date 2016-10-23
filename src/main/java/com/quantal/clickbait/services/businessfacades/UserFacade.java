package com.quantal.clickbait.services.businessfacades;

import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.dto.ResponseMessageDTO;
import com.quantal.clickbait.dto.UserDTO;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.workers.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    return toRESTResponse(createdUserDTO, HttpStatus.OK,null);
  }

   public ResponseEntity<?> findUser(long id){
     User foundUser = userService.findById(id);
     UserDTO foundUserDTO = mapper.map(foundUser, UserDTO.class);
     return toRESTResponse(foundUserDTO);

   }

  public ResponseEntity<?> findAllUsers(){
    List<User> foundUsers = userService.findAllUsers();
    List<UserDTO> foundUsersDTO =  mapper.mapAsList(foundUsers, UserDTO.class);
    return toRESTResponse(foundUsersDTO);
  }

  public ResponseEntity<?> updateUser(long userId, UserDTO updateDataDTO){

    User user = mapper.map(updateDataDTO, User.class);
    user.setId(userId);
    User updatedUser = userService.update(user);
    return toRESTResponse(updatedUser);
  }

  public ResponseEntity<?> deleteUser(long userId){

     userService.deleteById(userId);
    return toRESTResponse(new ResponseMessageDTO("User successfully deletes", 100));
  }
}
