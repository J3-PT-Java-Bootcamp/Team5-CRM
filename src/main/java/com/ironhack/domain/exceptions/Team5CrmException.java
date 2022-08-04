package com.ironhack.domain.exceptions;

public class Team5CrmException extends Exception{

    public Team5CrmException() {
    }

    public Team5CrmException(String message) {
        super(message);
    }

    public Team5CrmException(String message, Throwable cause) {
        super(message, cause);
    }

    public Team5CrmException(Throwable cause) {
        super(cause);
    }

    public Team5CrmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
