package com.medilab.microservice_backend_assessor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilab.microservice_backend_assessor.dto.NewTriggerDto;
import com.medilab.microservice_backend_assessor.dto.UpdateTriggerDto;
import com.medilab.microservice_backend_assessor.service.TriggerService;
import com.medilab.microservice_backend_assessor.stub.TriggerRepositoryImplStub;
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

public class TriggerControllerTest {
    
    private final TriggerRepositoryImplStub repository = new TriggerRepositoryImplStub();
    private final TriggerService service = new TriggerService(repository);
    
    @InjectMocks
    private final TriggerController controller = new TriggerController(service);
    
    private MockMvc mvc;
    
    private ObjectMapper objectMapper;
    
    private final String URL = "/microservice-backend-assessor/v1/trigger";
    
    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
        this.objectMapper = new ObjectMapper();
    }
    
    @Test
    public void getAllShouldReturnAllTriggersTest() throws Exception {
        int size = repository.findAll()
                .size();
        
        mvc.perform(get(URL + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(size))
                .andDo(print());
    }
    
    @Test
    public void getByIdShouldReturnTriggerByIdTest() throws Exception {
        String id = "73052162-5644-47c6-a76e-a3e6ee17eedb";
        String name = "Hémoglobine A1C";
        
        mvc.perform(get(URL + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andDo(print());
    }
    
    @Test
    public void saveShouldSaveANewTriggerTest() throws Exception {
        NewTriggerDto newTriggerDto = new NewTriggerDto("test");
        
        mvc.perform(
                        post(URL + "/save").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(newTriggerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"))
                .andDo(print());
    }
    
    @Test
    public void shouldUpdateATriggerTest() throws Exception {
        UpdateTriggerDto updateTriggerDto = new UpdateTriggerDto("90289de5-9084-4clp-8dec-f7c58669b875", "Boit");
        
        mvc.perform(patch(URL + "/update").contentType(
                                MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTriggerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updateTriggerDto.name()))
                .andDo(print());
    }
    
    @Test
    public void shouldDeleteATriggerTest() throws Exception {
        String id = "90289de5-9084-4clp-8dec-f7c58669b875";
        
        mvc.perform(delete(URL + "/delete/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
