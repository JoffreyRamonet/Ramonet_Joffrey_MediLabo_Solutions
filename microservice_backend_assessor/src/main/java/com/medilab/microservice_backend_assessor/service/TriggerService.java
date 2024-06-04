package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.dto.NewTriggerDto;
import com.medilab.microservice_backend_assessor.dto.UpdateTriggerDto;
import com.medilab.microservice_backend_assessor.error.DataNotFoundException;
import com.medilab.microservice_backend_assessor.error.TriggerAlreadyExistException;
import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.repository.TriggerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TriggerService implements TriggerUseCase {
    
    private TriggerRepository repository;
    
    @Override
    public List<Trigger> getAll() {
        
        List<Trigger> triggers = repository.findAll();
        
        log.debug("TriggerService - getAll - Notes list size: " + triggers.size());
        
        return triggers;
    }
    
    @Override
    public Optional<Trigger> getById(String id) {
        
        Optional<Trigger> trigger = repository.findById(id);
        
        log.debug("TriggerService - getById - id parsed: " + id + "trigger is present: " + trigger.isPresent());
        
        return trigger;
        }
    
    @Override
    public Trigger save(NewTriggerDto newTriggerDto) throws TriggerAlreadyExistException {
        if(repository.findAll()
                .stream()
                .map(Trigger::getName)
                .toList()
                .contains(newTriggerDto.getName())) {
            throw new TriggerAlreadyExistException("This trigger already Exist");
        } else {
            
            Trigger newTrigger = new Trigger(upperCaseCheck(newTriggerDto.getName()));
            log.debug("TriggerService - save - Note created: " + newTrigger.getId());
            
            return repository.save(newTrigger);
        }
    }
    
    @Override
    public Trigger update(UpdateTriggerDto updateTriggerDto) {
        
        log.debug("TriggerService - update - Note to update: " + updateTriggerDto.id() + ", information to update: " + updateTriggerDto.name());
        
        Optional<Trigger> triggerfound = repository.findById(updateTriggerDto.id());
        Trigger triggerToUpdate;
        
        if(triggerfound.isPresent()) {
            triggerToUpdate = triggerfound.get();
        } else {
            throw new DataNotFoundException("No trigger found.");
        }
        
        if(updateTriggerDto.name() != null) {
            triggerToUpdate.setName(updateTriggerDto.name());
        }
        
        return repository.save(triggerToUpdate);
    }
    
    @Override
    public void delete(String id) {
        
        log.debug("TriggerService - deleteById - Id to delete: " + id);
        
        repository.delete(id);
    }
    
    private String upperCaseCheck(String name) {
        char[] nameChar = name.toCharArray();
        nameChar[0] = Character.toUpperCase(nameChar[0]);
        
        return new String(nameChar);
    }
}
