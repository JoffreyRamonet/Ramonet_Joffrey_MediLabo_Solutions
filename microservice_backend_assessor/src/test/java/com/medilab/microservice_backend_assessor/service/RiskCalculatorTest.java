package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.model.RiskResult;
import com.medilab.microservice_backend_assessor.stub.NoteClientImplStub;
import com.medilab.microservice_backend_assessor.stub.PatientClientImplStub;
import com.medilab.microservice_backend_assessor.stub.TriggerRepositoryImplStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RiskCalculatorTest {
    
    private final PatientClientImplStub patientClient = new PatientClientImplStub();
    private final NoteClientImplStub noteClient = new NoteClientImplStub();
    private final TriggerRepositoryImplStub repository = new TriggerRepositoryImplStub();
    private final TriggerCounter triggerCounter = new TriggerCounter(noteClient, repository);
    private final AgeCalculator ageCalculator = new AgeCalculator();
    private final RiskCalculator service = new RiskCalculator(patientClient, triggerCounter, ageCalculator);
    
    
    
    @Test
    void shouldReturnRiskNoneTest(){
        String id = "73052162-5644-47c6-a76e-a3e6ee17eedb";
        RiskResult result = service.riskCalculator(id);
        
        assertEquals("none", result.result());
    }
    
    @Test
    void shouldReturnRiskInDangerTest(){
        String id = "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd";
        RiskResult result = service.riskCalculator(id);
        
        assertEquals("indanger", result.result());
    }
    
    @Test
    void shouldReturnRiskborderlineTest(){
        String id = "90229de5-9024-4cdd-8dec-f7c54669b336";
        RiskResult result = service.riskCalculator(id);
        
        assertEquals("borderline", result.result());
    }
    
    @Test
    void shouldReturnRiskearlyonsetTest(){
        String id = "d341589d-906c-4d67-8f24-d3db885efe9b";
        RiskResult result = service.riskCalculator(id);
        
        assertEquals("earlyonset", result.result());
    }
}
