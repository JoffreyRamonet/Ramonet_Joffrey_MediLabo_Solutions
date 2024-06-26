package com.medilab.microserviceback.controller;

import com.medilab.microserviceback.dto.PatientSaveDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilab.microserviceback.dto.PatientUpdateDto;
import com.medilab.microserviceback.microservice_client.NoteClientImpl;
import com.medilab.microserviceback.model.Patient;
import com.medilab.microserviceback.service.PatientService;
import com.medilab.microserviceback.stub.NoteClientImplStub;
import com.medilab.microserviceback.stub.PatientRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class PatientControllerTest {
    
    private final PatientRepositoryStub repository = new PatientRepositoryStub();
    private final NoteClientImplStub client = new NoteClientImplStub();
    private final PatientService service = new PatientService(repository, client);
    
    @InjectMocks
    private final PatientController controller = new PatientController(service);
    
    private MockMvc mvc;
    
    private ObjectMapper objectMapper;
    
    private final String URL = "/microservice-backend-patient/v1/patient";
    
    @BeforeEach
    public void setup(){
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.objectMapper = new ObjectMapper();
    }

    
    @Test
    void shouldReturnAllPatientsTest() throws Exception {
        int size = repository.findAll()
                .size();
        
        mvc.perform(get(URL + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(size))
                .andDo(print());
    }
    
    @Test
    void shouldReturnPatientByIdTest() throws Exception {
        String id = "73052162-5644-47c6-a76e-a3e6ee17eedb";
        Patient result = repository.findById(id)
                .get();
        
        mvc.perform(get(URL + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value(result.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(result.getLastName()))
                .andDo(print());
    }
    
    @Test
    void shouldSavePatientTest() throws Exception {
        PatientSaveDto personSaveDto =
                new PatientSaveDto("lastName", "firstName", "1993-02-05", "M", "my address", "111-555-8888");
        
        mvc.perform(post(URL + "/save").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personSaveDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdatePatientTest() throws Exception {
        PatientUpdateDto patientUpdateDto =
                new PatientUpdateDto("TestEarlyOnset", "Test", "F", "my New Address", "123-123-1234");
        
        mvc.perform(patch(URL + "/update").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(patientUpdateDto.firstName()))
                .andExpect(jsonPath("$.address").value(patientUpdateDto.address()))
                .andDo(print());
    }

    @Test
    void shouldDeletePatientByIdTest() throws Exception {
        String id = "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd";

        mvc.perform(delete(URL + "/delete/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
