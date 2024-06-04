package com.medilab.microservice_backend_assessor.controller;

import com.medilab.microservice_backend_assessor.service.AgeCalculator;
import com.medilab.microservice_backend_assessor.service.RiskCalculator;
import com.medilab.microservice_backend_assessor.service.TriggerCounter;
import com.medilab.microservice_backend_assessor.stub.NoteClientImplStub;
import com.medilab.microservice_backend_assessor.stub.PatientClientImplStub;
import com.medilab.microservice_backend_assessor.stub.TriggerRepositoryImplStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AccessorControllerTest {
    
    private final PatientClientImplStub patientClient = new PatientClientImplStub();
    private final NoteClientImplStub noteClient = new NoteClientImplStub();
    private final TriggerRepositoryImplStub repository = new TriggerRepositoryImplStub();
    private final TriggerCounter triggerCounter = new TriggerCounter(noteClient, repository);
    private final AgeCalculator ageCalculator = new AgeCalculator();
    private final RiskCalculator riskCalculator = new RiskCalculator(patientClient, triggerCounter, ageCalculator);
   
    @InjectMocks
    private final AccessorController controller = new AccessorController(riskCalculator);
    
    private MockMvc mvc;
    
    @BeforeEach
    public void setup(){
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    void shouldReturnTheRiskResultTest() throws Exception {
        String id = "73052162-5644-47c6-a76e-a3e6ee17eedb";
        
        mvc.perform(get("/microservice_backend_accessor/v1/accessor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("none"))
                .andDo(print());
    }
}
