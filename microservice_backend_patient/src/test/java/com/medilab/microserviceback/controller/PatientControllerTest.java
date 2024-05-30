package com.medilab.microserviceback.controller;

import com.medilab.microserviceback.dto.PatientSaveDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilab.microserviceback.dto.PatientUpdateDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PatientController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(TestConfiguration.class)
public class PatientControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;

    @Order(1)
    @Test
    void shouldReturnAllPatientsTest() throws Exception {
        mvc.perform(get("/microservice_back/v1/patient/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(4))
                .andDo(print());
    }
    
    @Order(2)
    @Test
    void shouldReturnPatientByIdTest() throws Exception {
        String id = "73052162-5644-47c6-a76e-a3e6ee17eedb";
        
        mvc.perform(get("/microservice_back/v1/patient/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("TestNone"))
                .andDo(print());
    }
    
    @Order(3)
    @Test
    void shouldReturnPatientByNameTest() throws Exception {
        String firstName = "Test";
        String lastName = "TestNone";
        
        mvc.perform(get("/microservice_back/v1/patient/{firstName}/{lastName}", firstName, lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("73052162-5644-47c6-a76e-a3e6ee17eedb"))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andDo(print());
    }
    
    @Order(4)
    @Test
    void shouldSavePatientTest() throws Exception {
        PatientSaveDto personSaveDto =
                new PatientSaveDto("lastName", "firstName", "1993-02-05", "M", "my address", "111-555-8888");
        
        mvc.perform(post("/microservice_back/v1/patient/save").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(personSaveDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Order(5)
    @Test
    void shouldUpdatePatientTest() throws Exception {
        PatientUpdateDto patientUpdateDto =
                new PatientUpdateDto("TestEarlyOnset", "Test", "F", "my New Address", "123-123-1234");
        
        mvc.perform(patch("microservice_back/v1/patient/update").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(patientUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("TestEarlyOnset"))
                .andExpect(jsonPath("$.address").value(patientUpdateDto.address()))
                .andDo(print());
    }
    
    @Order(6)
    @Test
    void shouldDeletePatientByIdTest() throws Exception {
        String id = "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd";
        
        mvc.perform(delete("microservice_back/v1/patient/delete/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Order(7)
    @Test
    void shouldDeletePatientByNameTest() throws Exception {
        String firstName = "Test";
        String lastName = "TestInDanger";
        
        mvc.perform(delete("microservice_back/v1/patient/delete/{firstName}/{lastName}", firstName, lastName))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
