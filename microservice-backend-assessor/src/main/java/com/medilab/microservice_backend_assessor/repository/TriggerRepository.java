package com.medilab.microservice_backend_assessor.repository;

import com.medilab.microservice_backend_assessor.model.Trigger;

import java.util.List;
import java.util.Optional;

/**
 * Interface to give mandatory methods to repository classes.
 */
public interface TriggerRepository {
    public List<Trigger> findAll();
    
    public Optional<Trigger> findById(String id);
    
    public Trigger save(Trigger trigger);
    
    public void delete(String id);
}
