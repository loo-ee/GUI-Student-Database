package com.louie.guistudentdatabase.Login;

public class ExceptionHandling extends Exception{
    private String message;

    public ExceptionHandling(String message) {
        super(message);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
