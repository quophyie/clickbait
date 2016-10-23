package com.quantal.clickbait.controllers;

import com.quantal.clickbait.dto.UserDTO;
import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.services.businessfacades.UserFacade;
import com.quantal.clickbait.services.workers.interfaces.UserService;
import com.quantal.clickbait.dto.ResponseMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dman on 13/09/2016.
 */


@RestController()
@RequestMapping("/users")
public class UserController {


  @Autowired
  private UserService userService;

  @Autowired
  private UserFacade userFacade;

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO user) {

   /** HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    User addedUser = userService.add(user);
    ResponseEntity<User> res = new ResponseEntity<>(addedUser, /*headers, * /HttpStatus.OK);*/
    ResponseEntity<?> res = userFacade.createUser(user);
    return res;
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> findUser(@PathVariable("id") long userId) {
    /*
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    User user = userService.findById(userId);
    ResponseEntity<User> res = new ResponseEntity<>(user, /*headers, * /HttpStatus.OK);
    return res;
    */
    return userFacade.findUser(userId);
  }

  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> findAll() {

    /*HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    List<User> user = userService.findAllUsers();
    ResponseEntity<List<User>> res = new ResponseEntity<>(user, /*headers, * /HttpStatus.OK);
    return res;*/
    return userFacade.findAllUsers();
  }

  public UserController() {
  }

  @GetMapping(path = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> home(@RequestParam("id") long userId) {

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    ResponseEntity<ArrayList<String>> res = new ResponseEntity<>(new ArrayList<String>(), /*headers, */HttpStatus.OK);
    return res;
  }


  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateUser(@PathVariable("id") long userId, @RequestBody UserDTO updateData) {

    /*
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    User bUpdated = userService.update(updateData);
    ResponseMessageDTO response  = new ResponseMessageDTO();
    response.setMessage("OK");
    ResponseEntity<ResponseMessageDTO> res = new ResponseEntity<>(response, headers, HttpStatus.OK);
    return res;*/

    return userFacade.updateUser(userId, updateData);
  }

  @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteUser(@PathVariable("id") long userId) {

    /*
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    User bUpdated = userService.update(updateData);
    ResponseMessageDTO response  = new ResponseMessageDTO();
    response.setMessage("OK");
    ResponseEntity<ResponseMessageDTO> res = new ResponseEntity<>(response, headers, HttpStatus.OK);
    return res;*/

    return userFacade.deleteUser(userId);
  }


}
