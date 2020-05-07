package com.oyo.hms.txnservice.response;

import lombok.Data;

@Data
public class BaseMessageResponse {

  private Boolean status;
  private String message;

  public BaseMessageResponse(){

  }

  public BaseMessageResponse(Boolean status, String message) {
    this.status = status;
    this.message = message;
  }
}
