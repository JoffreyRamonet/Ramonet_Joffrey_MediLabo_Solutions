package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.stub.NoteClientImplStub;
import com.medilab.microservice_backend_assessor.stub.TriggerRepositoryImplStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TriggerCounterTest {
    
    private final NoteClientImplStub noteClient = new NoteClientImplStub();
    private final TriggerRepositoryImplStub repository = new TriggerRepositoryImplStub();
    private final TriggerCounter triggerCounter = new TriggerCounter(noteClient, repository);
    
    private final String ID = "d341589d-906c-4d67-8f24-d3db885efe9b";
    
    @Test
    void triggerCounterShouldReturnSixTest(){
        
        Integer result = triggerCounter.triggerCounter(ID);
        
        assertEquals(7, result);
    }
    
}
