package com.medilab.microservice_backend_assessor.repository;

import com.medilab.microservice_backend_assessor.model.Trigger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implement the TriggerRepository.
 * Forwards requests to ClientMySql to query the MySQL database.
 *
 * @see TriggerRepository
 * @see ClientMySql
 */
@Repository
@AllArgsConstructor
public class TriggerRepositoryImpl implements TriggerRepository {
    
    private ClientMySql repository;
    
    @Override
    public List<Trigger> findAll() {
        return repository.findAll();
    }
    
    @Override
    public Optional<Trigger> findById(String id) {
        return repository.findById(id);
    }
    
    @Override
    public Trigger save(Trigger trigger) {
        return repository.save(trigger);
    }
    
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
