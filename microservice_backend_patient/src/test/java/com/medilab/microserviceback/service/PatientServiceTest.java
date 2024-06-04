package com.medilab.microserviceback.service;

import com.medilab.microserviceback.dto.PatientSaveDto;
import com.medilab.microserviceback.dto.PatientUpdateDto;
import com.medilab.microserviceback.model.Patient;
import com.medilab.microserviceback.stub.PatientRepositoryStub;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class PatientServiceTest {
    
    
    private final PatientRepositoryStub repository = new PatientRepositoryStub();;
    private final PatientService service = new PatientService(repository);
    
    
    @Test
    void shouldReturnAllPatientsTest() {
        List<Patient> result = service.getAll();
        
        assertEquals(repository.findAll()
                .size(), result.size());
    }
    
    @Test
    void shouldReturnPatientByIdTest() {
        String id = "73052162-5644-47c6-a76e-a3e6ee17eedb";
        Optional<Patient> result = service.getById(id);
        
        assertTrue(result.isPresent());
        assertEquals(id, result.get()
                .getID());
    }
    
    @Test
    void shouldReturnPatientByNameTest() {
        String lastName = "TestNone";
        String firstName = "Test";
        
        Optional<Patient> result = service.getByName(firstName, lastName);
        
        assertTrue(result.isPresent());
        assertEquals(firstName, result.get()
                .getFirstName());
    }
    
    @Test
    void shouldSavePatientTest() {
        PatientSaveDto patientSaveDto =
                new PatientSaveDto("lastName", "firstName", "1993-02-05", "M", "my address", "111-555-8888");
        
        Patient result = service.save(patientSaveDto);
        
        assertEquals(patientSaveDto.firstName(), result.getFirstName());
        assertEquals(patientSaveDto.lastName(), result.getLastName());
    }
    
    @Test
    void shouldUpdateGenderOnlyOfPatientTest() {
        PatientUpdateDto patientUpdateDto = new PatientUpdateDto("TestEarlyOnset", "Test", "M", null, null);
        
        Patient result = service.update(patientUpdateDto);
        
        assertEquals(patientUpdateDto.firstName(), result.getFirstName());
        assertEquals(patientUpdateDto.gender(), result.getGender());
    }
    
    @Test
    void shouldUpdateAddressOnlyOfPatientTest() {
        PatientUpdateDto patientUpdateDto = new PatientUpdateDto("TestEarlyOnset", "Test", null, "new address", null);
        
        Patient result = service.update(patientUpdateDto);
        
        assertEquals(patientUpdateDto.firstName(), result.getFirstName());
        assertEquals(patientUpdateDto.address(), result.getAddress());
    }
    
    @Test
    void shouldUpdatePhoneOnlyOfPatientTest() {
        PatientUpdateDto patientUpdateDto = new PatientUpdateDto("TestEarlyOnset", "Test", null, null, "123-123-1234");
        
        Patient result = service.update(patientUpdateDto);
        
        assertEquals(patientUpdateDto.firstName(), result.getFirstName());
        assertEquals(patientUpdateDto.phone(), result.getPhone());
    }
    
    @Test
    void shouldDeletePatientByIdTest() {
        String id = "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd";
        
        service.deleteById(id);
        
        assertDoesNotThrow(() -> service.deleteById(id));
    }

}
