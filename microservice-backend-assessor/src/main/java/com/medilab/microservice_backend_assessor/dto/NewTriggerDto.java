package com.medilab.microservice_backend_assessor.dto;

import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.service.TriggerService;


/**
 * The Data Transfer Object to save a new Trigger.
 *
 * @param name - String
 * @see Trigger
 * @see TriggerService#save(NewTriggerDto) (UpdateTriggerDto)
 */

public record NewTriggerDto(String name) {
    

}
