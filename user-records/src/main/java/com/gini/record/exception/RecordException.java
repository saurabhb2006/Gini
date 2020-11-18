package com.gini.record.exception;

/**
 * Class to handle if an item already exists in the database.
 */
public class RecordException extends RuntimeException{

    private String message;
    private String description;

    public RecordException() {
    }

    public RecordException(String message, String description) {

        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

}
