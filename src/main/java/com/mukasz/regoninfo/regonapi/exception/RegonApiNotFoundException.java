package com.mukasz.regoninfo.regonapi.exception;

public class RegonApiNotFoundException extends RuntimeException {
    public RegonApiNotFoundException() {
    }

    public RegonApiNotFoundException(String message) {
        super(message);
    }

    public RegonApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegonApiNotFoundException(Throwable cause) {
        super(cause);
    }
}
