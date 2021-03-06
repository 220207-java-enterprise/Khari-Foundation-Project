package com.revature.foundations.util.exceptions;

public class ForbiddenException extends ResourceNotFoundException{
    public ForbiddenException() {
        super("Your account cannot perform this action at this time");
    }

    public ForbiddenException(String message) {
        super();
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
