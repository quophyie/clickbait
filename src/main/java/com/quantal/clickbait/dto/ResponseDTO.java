package com.quantal.clickbait.dto;

/**
 * Created by dman on 10/10/2016.
 */
public class ResponseDTO<TData> extends ResponseMessageDTO {

  private TData data;

  public ResponseDTO(){}

  public ResponseDTO(String message, int code, TData data){
    super(message, code);
    this.data = data;
  }

  public ResponseDTO(TData data) {
    this.data = data;
  }

  public TData getData() {
    return data;
  }

  public void setData(TData data) {
    this.data = data;
  }
}
