package com.medilab.microservice_backend_assessor.stub;

import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.repository.TriggerRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class TriggerRepositoryImplStub implements TriggerRepository {
    
    private DataBaseStub dataBaseStub = new DataBaseStub();
    private final List<Trigger> triggers = new CopyOnWriteArrayList<>(dataBaseStub.getTriggers());
    
    @Override
    public List<Trigger> findAll() {
        return triggers;
    }
    
    @Override
    public Optional<Trigger> findById(String id) {
        Trigger triggerFound = triggers.stream()
                .filter(trigger -> trigger.getId()
                        .equals(id))
                .toList()
                .getFirst();
        
        return Optional.of(triggerFound);
    }
    
    @Override
    public Trigger save(Trigger trigger) {
        Trigger result = null;
        
        for(Trigger t : triggers) {
            if(t.getId()
                    .equals(trigger.getId())) {
                t.setName(trigger.getName());
                return t;
            } else {
                triggers.add(trigger);
            }
        }
        return trigger;
    }
    
    @Override
    public void delete(String id) {
        if(findById(id).isEmpty()) {
            triggers.remove(id);
        }
    }
}
