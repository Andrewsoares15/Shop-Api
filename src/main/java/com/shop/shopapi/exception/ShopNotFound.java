package com.shop.shopapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShopNotFound extends RuntimeException{

    public ShopNotFound(String message){
        super(message);
    }
}
