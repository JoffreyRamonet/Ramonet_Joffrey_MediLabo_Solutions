package com.medilab.microservice_backend_assessor.dto;

import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.service.TriggerService;

/**
 * The Data Transfer Object to update a Trigger.
 *
 * @param id   - String - id of the trigger.
 * @param name - String
 * @see Trigger
 * @see TriggerService#update(UpdateTriggerDto)
 */
public record UpdateTriggerDto(String id, String name) {
}
