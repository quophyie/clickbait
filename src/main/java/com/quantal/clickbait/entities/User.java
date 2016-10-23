package com.quantal.clickbait.entities;

import com.quantal.clickbait.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by dman on 13/09/2016.
 */
@Entity
@Table(name = "USERS")
public class User extends BaseNumericKeyEntity<Long> implements Serializable {

  /*@GeneratedValue(strategy= GenerationType.IDENTITY)
  @Id
  private Long id; */
  private String email;
  private String password;
  private Gender gender;
  private String firstName;
  private String lastName;
  private LocalDate createdDate;
  private LocalDate dob;

  /*@Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  } */

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
