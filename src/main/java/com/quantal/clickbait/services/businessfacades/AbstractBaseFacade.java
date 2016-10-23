package com.quantal.clickbait.services.businessfacades;

import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.objectmapper.OrikaBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dman on 23/10/2016.
 */

public abstract class AbstractBaseFacade {
  @Autowired
  protected OrikaBeanMapper mapper;

  public static<TResponseData> ResponseEntity<?> toRESTResponse(ResponseDTO<TResponseData> reponseDTO, HttpStatus httpStatus, HttpHeaders httpHeaders){

    ResponseEntity<ResponseDTO<TResponseData>> response;

    if (httpHeaders != null){
       response = new ResponseEntity<>(reponseDTO, httpHeaders, httpStatus);
    } else {
      response = new ResponseEntity<>(reponseDTO, /*headers, */httpStatus);
    }
    return response;
  }
}
