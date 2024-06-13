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

/**
 * The main controller of the microservice-backend-patient, expose all endpoints.
 * <p>
 * Use GET - POST - PATCH - DELETE http requests.
 * The default url to call the controller is:
 * {@snippet lang = "Properties"}:
 * "/microservice_backend_patient/v1/patient"
 * </p>
 * <p>
 * Requests return Patient(s) model.
 *
 * @see Patient
 * Requests require PatientSaveDto to save a new patient in the database.
 * @see PatientSaveDto
 * Requests require PatientUpdateDto to modify a patient in the database.
 * @see PatientUpdateDto
 * Requires a PatientUseCase to apply business treatments and receive data.
 * @see PatientUseCase
 * </p>
 * <p>
 * Requests can throw exceptions if the patient is not found.
 * @see DataNotFoundException
 * Save request can throw an exception if the patient already exist in the database.
 * @see PatientAlreadyExistException
 * </p>
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/microservice-backend-patient/v1/patient")
public class PatientController {
    
    private final PatientUseCase patientService;
    
    @GetMapping("/all")
    public List<Patient> getAll() {
        
        List<Patient> patients = patientService.getAll();
        
        log.debug("PatientController - getAll - Notes list size: " + patients.size());
        return patients;
    }
    
    @GetMapping("/{id}")
    public Patient getById(
            @PathVariable
            final String id) {
        
        Optional<Patient> patient = patientService.getById(id);
        
        if(patient.isEmpty()) {
            throw new DataNotFoundException("Patient not found");
        }
        
        log.debug("PatientController - getById - id parsed: " + id);
        
        return patient.get();
    }
    
    @PostMapping("/save")
    public Patient save(
            @RequestBody PatientSaveDto patient) throws PatientAlreadyExistException {
        
        log.debug("PatientController - save - Patient parsed to save: " + patient.firstName() + " - " +
                patient.lastName() + " - " + patient.birthDate() + " - " + patient.gender() + " - " +
                patient.address() + " - " + patient.phone());
        
        if(patientService.getByName(patient.firstName(), patient.lastName())
                .isPresent()) {
            throw new PatientAlreadyExistException("Patient already exist");
        }
        
        return patientService.save(patient);
    }
    
    @PatchMapping("/update")
    public Patient update(
            @RequestBody PatientUpdateDto patientUpdateDto) {
        
        log.debug("PatientController - update - Patients to update: " + patientUpdateDto.firstName() + ", " +
                patientUpdateDto.lastName() + "Patient's informations to update: " + patientUpdateDto.gender() + " - " +
                patientUpdateDto.address() + " - " + patientUpdateDto.phone());
        
        return patientService.update(patientUpdateDto);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteById(
            @PathVariable
            final String id) {
        
        if(patientService.getById(id)
                .isEmpty()) {
            throw new DataNotFoundException("Patient not found");
        } else {
            patientService.deleteById(id);
        }
        
        log.debug("PatientController - deleteById - Id to delete: " + id);
    }
}
