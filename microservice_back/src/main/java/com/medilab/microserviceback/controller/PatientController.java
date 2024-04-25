package com.medilab.microserviceback.controller;

import com.medilab.microserviceback.dto.PatientSaveDto;
import com.medilab.microserviceback.dto.PatientUpdateDto;
import com.medilab.microserviceback.error.DataNotFoundException;
import com.medilab.microserviceback.error.PatientAlreadyExistException;
import com.medilab.microserviceback.model.Patient;
import com.medilab.microserviceback.service.PatientUseCase;
import jakarta.transaction.Transactional;
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
@AllArgsConstructor
@Slf4j
@RequestMapping("/microservice_back/v1/patient")
public class PatientController {
    
    private final PatientUseCase patientService;
    
    @GetMapping("/all")
    public List<Patient> getAll() {
        List<Patient> patients = patientService.getAll();
        
        log.debug("Patients list size: " + patients.size());
        return patients;
    }
    
    @GetMapping("/{firstname}/{lastname}")
    public Patient getByNames(
            @PathVariable
            final String firstname,
            @PathVariable
            final String lastname) {
        
        Optional<Patient> patient = patientService.getByName(firstname, lastname);
        
        if(patient.isEmpty()) {
            throw new DataNotFoundException("Patient not found");
        }
        log.debug("Patient found: " + patient.get()
                .getID()
                .toString());
        return patient.get();
    }
    
    @PostMapping("/save")
    public Patient save(
            @RequestBody PatientSaveDto patient) throws PatientAlreadyExistException {
        log.debug("Patient parsed to save: " + patient.firstName() + " - " + patient.lastName() + " - " +
                patient.birthDate() + " - " + patient.gender() + " - " + patient.address() + " - " +
                patient.phone());
        
        if(patientService.getByName(patient.firstName(), patient.lastName())
                .isPresent()) {
            throw new PatientAlreadyExistException("Patient already exist");
        }
        
        return patientService.save(patient);
    }
    
    @PatchMapping("/update")
    public Patient update(
            @RequestBody PatientUpdateDto patientUpdateDto) {
        
        log.debug("Patients to update: " + patientUpdateDto.firstName() + ", " + patientUpdateDto.lastName() +
                "Patient's informations to update: " +
                patientUpdateDto.gender() + " - " + patientUpdateDto.address() + " - " + patientUpdateDto.phone());
        
        return patientService.update(patientUpdateDto);
    }
    
    @Transactional
    @DeleteMapping("/delete/{firstname}/{lastname}")
    public void deleteByName(
            @PathVariable
            final String firstname,
            @PathVariable
            final String lastname) {
        
        
        if(patientService.getByName(firstname, lastname)
                .isEmpty()) {
            throw new DataNotFoundException("Patient not found");
        }
        
        patientService.deleteByName(firstname, lastname);
    }
}
