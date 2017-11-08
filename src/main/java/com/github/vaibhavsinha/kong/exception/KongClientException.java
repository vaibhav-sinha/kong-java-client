package com.github.vaibhavsinha.kong.exception;

/**
 * Created by vaibhav on 13/06/17.
 *
 * Updated by dvilela on 11/08/17.
 */
public class KongClientException extends RuntimeException {

    private int code;

    private String error;

    public KongClientException(String message) {
        super(message);
    }

    public KongClientException(String message, int code, String error) {
        super(message);
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
