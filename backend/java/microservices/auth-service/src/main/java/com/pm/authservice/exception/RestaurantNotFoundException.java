package com.pm.authservice.exception;

public class RestaurantNotFoundException extends RuntimeException {

  public RestaurantNotFoundException(String message) {
    super(message);
  }
}
