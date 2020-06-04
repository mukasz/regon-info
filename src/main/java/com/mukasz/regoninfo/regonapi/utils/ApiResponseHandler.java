package com.mukasz.regoninfo.regonapi.utils;

import com.mukasz.regoninfo.regonapi.exception.RegonApiNotFoundException;
import com.mukasz.regoninfo.regonapi.model.RegonApiObject;

import java.util.Optional;

public class ApiResponseHandler {
    private final static String NOT_FOUND_CODE = "4";

    public static <T extends RegonApiObject> void handleErrors(T response) {
        String errorCode = Optional.ofNullable(response.getErrorCode()).orElse("");

        switch (errorCode) {
            case NOT_FOUND_CODE: {
                throw new RegonApiNotFoundException(response.getErrorMessageEn());
            }
        }
    }
}
