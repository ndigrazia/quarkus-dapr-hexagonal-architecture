package com.telefonica.hispam.routerInventory.domain.exception;

public class RouterException extends RuntimeException {

    private RouterException(String message) {
        super(message);
    }

    private RouterException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RouterException create(String message) {
        return new RouterException(message);
    }

    public static RouterException create(String message, Throwable cause) {
        return new RouterException(message, cause);
    }

    public String toJson() {
       return  "{\"message\":\"" + getMessage() + "\"}";
    }
  
}
