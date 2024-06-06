package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.dto.NewTriggerDto;
import com.medilab.microservice_backend_assessor.dto.UpdateTriggerDto;
import com.medilab.microservice_backend_assessor.error.DataNotFoundException;
import com.medilab.microservice_backend_assessor.error.TriggerAlreadyExistException;
import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.stub.TriggerRepositoryImplStub;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class TriggerServiceTest {
    
    private final TriggerRepositoryImplStub repository = new TriggerRepositoryImplStub();
    private final TriggerService service = new TriggerService(repository);
    
    private final String ID = "73052162-5644-47c6-a76e-a3e6ee17eedb";
    
    @Test
    void getAllShouldReturnAllTriggersTest() {
        int size = repository.findAll()
                .size();
        
        List<Trigger> result = service.getAll();
        
        assertEquals(size, result.size());
    }
    
    @Test
    void getByIdShouldReturnTheCorrectTriggerTest() {
        Trigger trigger = repository.findById(ID)
                .get();
        
        Trigger result = service.getById(ID).get();
        
        assertEquals(trigger, result);
    }
    
    @Test
    void saveShouldSaveANewTriggerTest() throws TriggerAlreadyExistException {
        NewTriggerDto newTriggerDto = new NewTriggerDto("Test");
        
        Trigger result = service.save(newTriggerDto);
        
        assertEquals(newTriggerDto.name(), result.getName());
        repository.delete(result.getId());
    }
    
    @Test
    void saveShouldReturnAnExceptionTest(){
        NewTriggerDto newTriggerDto = new NewTriggerDto("Fumeur");

        
        TriggerAlreadyExistException triggerAlreadyExistException = assertThrows(TriggerAlreadyExistException.class, () -> service.save(newTriggerDto));
        
        assertTrue("This trigger already Exist".contains(triggerAlreadyExistException.getMessage()));
    }
    
    @Test
    void updateShouldModifyTheTriggerNameTest(){
        UpdateTriggerDto updateTriggerDto = new UpdateTriggerDto("90229de5-9024-4cdd-8dec-f7c54669b336", "Microalbumines");
        
        Trigger result = service.update(updateTriggerDto);
        
        assertEquals(updateTriggerDto.name(), result.getName());
    }
    
    @Test
    void deleteShouldDeleteATriggerTest(){
        repository.save(new Trigger("test", "test"));
        
      assertDoesNotThrow(() -> service.delete("test"));
        
    }
    
}
