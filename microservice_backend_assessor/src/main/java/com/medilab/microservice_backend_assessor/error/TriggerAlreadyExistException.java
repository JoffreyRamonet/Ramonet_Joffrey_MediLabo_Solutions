package com.medilab.microservice_backend_assessor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TriggerAlreadyExistException extends Exception {
    public TriggerAlreadyExistException(String message) {
        super(message);
    }
}
