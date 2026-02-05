package com.hdfc.bff.bffservicenew.exceptionHandler;

public class AuthServiceException extends RuntimeException{
    public AuthServiceException(String message) {
        super(message);
    }
}
