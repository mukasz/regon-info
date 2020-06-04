package com.mukasz.regoninfo.regonapi.exception;

public class RegonApiUnrecognizeException extends RuntimeException {
    public RegonApiUnrecognizeException() {
    }

    public RegonApiUnrecognizeException(String message) {
        super(message);
    }

    public RegonApiUnrecognizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegonApiUnrecognizeException(Throwable cause) {
        super(cause);
    }
}
