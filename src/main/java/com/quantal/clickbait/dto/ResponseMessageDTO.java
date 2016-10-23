package com.quantal.clickbait.dto;

/**
 * Created by dman on 15/09/2016.
 */
public class ResponseMessageDTO {
  private String message;
  private int code;

  public ResponseMessageDTO(){}

  public ResponseMessageDTO(String message, int code) {
    this.code = code;
    this.message = message;

  }
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
