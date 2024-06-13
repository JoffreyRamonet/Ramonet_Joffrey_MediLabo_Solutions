package com.medilab.microservice_backend_assessor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The TriggerAlreadyExistException is throws when a method require to save a Trigger who are already present in the database.
 * Can add a NOT_ACCEPTABLE status for http requests with a message to argument the response.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TriggerAlreadyExistException extends Exception {
    public TriggerAlreadyExistException(String message) {
        super(message);
    }
}
