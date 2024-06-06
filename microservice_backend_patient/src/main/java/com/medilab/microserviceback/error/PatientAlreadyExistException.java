package com.medilab.microserviceback.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The PatientAlreadyExistException is throws when a method require to save a Patient who are already present in the database.
 * Can add a NOT_ACCEPTABLE status for http requests with a message to argument the response.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PatientAlreadyExistException extends Exception {
    public PatientAlreadyExistException(String message) {
        super(message);
    }
}
