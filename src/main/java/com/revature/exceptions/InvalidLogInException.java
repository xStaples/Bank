package com.revature.exceptions;

public class InvalidLogInException extends Exception{

    public InvalidLogInException() {
    }

    public InvalidLogInException(String message) {
        super(message);
    }

    public InvalidLogInException(Throwable cause) {
        super(cause);
    }

    public InvalidLogInException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLogInException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
