package com.quantal.clickbait.exceptions;

/**
 * Created by dman on 13/09/2016.
 */
public class InvalidIdException extends RuntimeException {

  public InvalidIdException(){
    super();
  }
  public InvalidIdException(String message){
    super(message);
  }
}
