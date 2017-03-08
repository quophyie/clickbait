package com.quantal.clickbait.dto;

import com.quantal.clickbait.validators.email.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by dman on 11/10/2016.
 */
public class LoginDTO implements BaseDTO {

  @ValidEmail
  private String email;

  @NotNull
  @NotEmpty
  private String password;

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
}
