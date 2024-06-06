package com.medilab.microservice_backend_assessor.service;


import com.medilab.microservice_backend_assessor.dto.NewTriggerDto;
import com.medilab.microservice_backend_assessor.dto.UpdateTriggerDto;
import com.medilab.microservice_backend_assessor.error.TriggerAlreadyExistException;
import com.medilab.microservice_backend_assessor.model.Trigger;

import java.util.List;
import java.util.Optional;

/**
 * Interface to give mandatory methods to the service class.
 */
public interface TriggerUseCase {
    public List<Trigger> getAll();
    
    public Optional<Trigger> getById(String id);
    
    public Trigger save(NewTriggerDto newTriggerDto) throws TriggerAlreadyExistException;
    
    public Trigger update(UpdateTriggerDto updateTriggerDto);
    
    public void delete(String id);
}
