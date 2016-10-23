package com.quantal.clickbait.validators.password;

import com.quantal.clickbait.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by dman on 23/10/2016.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){
    UserDTO user = (UserDTO) obj;
    return user.getPassword().equals(user.getConfirmedPassword());
  }
}