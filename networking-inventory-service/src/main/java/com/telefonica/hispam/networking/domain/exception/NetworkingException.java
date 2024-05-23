package com.telefonica.hispam.networking.domain.exception;

public class NetworkingException extends RuntimeException {

    private NetworkingException(String message) {
        super(message);
    }

    private NetworkingException(String message, Throwable cause) {
        super(message, cause);
    }

    public static NetworkingException create(String message) {
        return new NetworkingException(message);
    }

    public static NetworkingException create(String message, Throwable cause) {
        return new NetworkingException(message, cause);
    }

    public String toJson() {
       return  "{\"message\":\"" + getMessage() + "\"}";
    }
  
}
