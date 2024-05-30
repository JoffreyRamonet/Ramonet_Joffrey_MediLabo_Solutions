package com.medilab.microserviceback.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PatientAlreadyExistException extends Exception {
    public PatientAlreadyExistException(String message) {
        super(message);
    }
}
