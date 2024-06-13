package com.medilab.microservice_backend_assessor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The DataNotFoundException is throws when a method can found de data asked.
 * Can add a NOT_FOUND status for http requests with a message to argument the response.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
