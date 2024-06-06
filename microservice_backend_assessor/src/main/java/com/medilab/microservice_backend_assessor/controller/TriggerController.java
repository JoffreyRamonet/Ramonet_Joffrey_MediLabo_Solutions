package com.medilab.microservice_backend_assessor.controller;

import com.medilab.microservice_backend_assessor.dto.NewTriggerDto;
import com.medilab.microservice_backend_assessor.dto.UpdateTriggerDto;
import com.medilab.microservice_backend_assessor.error.DataNotFoundException;
import com.medilab.microservice_backend_assessor.error.TriggerAlreadyExistException;
import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.service.TriggerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/microservice_backend_assessor/v1/trigger")
@AllArgsConstructor
@Slf4j
public class TriggerController {
    

    private TriggerService service;

    @GetMapping("/all")
    public List<Trigger> getAll(){
        
        List<Trigger> triggers = service.getAll();
        
        log.debug("TriggerController - getAll - Notes list size: " + triggers.size());
        
        return triggers;
    }
    
    @GetMapping("/{id}")
    public Trigger getById(@PathVariable final String id){
        
        Optional<Trigger> trigger = service.getById(id);
        
        if(trigger.isEmpty()){
            throw new DataNotFoundException("Trigger not found");
        }
        
        log.debug("TriggerController - getById - id parsed: " + id);
        
        return trigger.get();
    }
    
    @PostMapping("/save")
    public Trigger save(@RequestBody final NewTriggerDto newTriggerDto) throws TriggerAlreadyExistException {
        
        log.debug("TriggerController - save - Notes parsed to save: " + newTriggerDto.name());
        
        return service.save(newTriggerDto);
    }
    
    @PatchMapping("/update")
    public Trigger update(@RequestBody final UpdateTriggerDto updateTriggerDto) {
        
        log.debug("NoteController - update - Note to update: " + updateTriggerDto.id() + ", information to update: " + updateTriggerDto.name());
        
        return service.update(updateTriggerDto);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable final String id){
        
        log.debug("NoteController - deleteById - Id to delete: " + id);
        
        service.delete(id);
    }
}
