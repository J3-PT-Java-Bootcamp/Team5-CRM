package com.ironhack.ui.exceptions;

import com.ironhack.domain.exceptions.Team5CrmException;

public class WrongInputException extends Team5CrmException {

    public WrongInputException() {
    }

    public WrongInputException(String message) {
        super(message);
    }

    public WrongInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongInputException(Throwable cause) {
        super(cause);
    }

    public WrongInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
