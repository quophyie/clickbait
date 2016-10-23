package com.quantal.clickbait.services.businessfacades;

import com.quantal.clickbait.dto.ResponseDTO;
import com.quantal.clickbait.objectmapper.OrikaBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Created by dman on 23/10/2016.
 */

public abstract class AbstractBaseFacade {
  @Autowired
  protected OrikaBeanMapper mapper;

  public static <TResponseDTOData> ResponseEntity<?> toRESTResponse(TResponseDTOData reponseDTOData, HttpStatus httpStatus, HttpHeaders httpHeaders){

    ResponseEntity<ResponseDTO<TResponseDTOData>> response;
    ResponseDTO<TResponseDTOData> responseDTO = new ResponseDTO<>(reponseDTOData);
    if (httpHeaders != null){
       response = new ResponseEntity<>(responseDTO, httpHeaders, httpStatus);
    } else {
      response = new ResponseEntity<>(responseDTO, httpStatus);
    }
    return response;
  }

  public static <TResponseDTOData> ResponseEntity<?> toRESTResponse(TResponseDTOData reponseDTOData, HttpStatus httpStatus){
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
    return toRESTResponse(reponseDTOData,httpStatus,headers);
  }


  public static <TResponseDTOData> ResponseEntity<?> toRESTResponse(TResponseDTOData reponseDTOData){
    return toRESTResponse(reponseDTOData,HttpStatus.OK);
  }
}
