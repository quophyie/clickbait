package com.quantal.clickbait.dto;

import com.quantal.clickbait.validators.email.ValidEmail;
import com.quantal.clickbait.validators.password.PasswordMatches;
import com.quantal.clickbait.enums.Gender;

import java.time.LocalDate;

/**
 * Created by dman on 13/09/2016.
 */
@PasswordMatches
public class UserDTO {

  private long id;
  @ValidEmail
  private String email;
  private String password;
  private String confirmedPassword;
  private Gender gender;
  private String firstName;
  private String lastName;
  private LocalDate createdDate;
  private LocalDate dob;

  public UserDTO() {

  }

  /** THE NON NO-ARGUMENT CONSTRUCTOR. IF WE UNCOMMENT THIS CONSTRUCTOR,
   * WE WILL HAVE TO MANUALLY SET THE PROPERTIES OTHERWISE, THEY WILL ALL BE NULL
   * IF USED WITH OrikaObjectMapper
  public UserDTO(User user) {
   this.email = user.getEmail();
  }*/

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmedPassword() {
    return confirmedPassword;
  }

  public void setConfirmedPassword(String confirmedPassword) {
    this.confirmedPassword = confirmedPassword;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }
}