package com.medilab.microserviceback.service;

import com.medilab.microserviceback.dto.PatientSaveDto;
import com.medilab.microserviceback.dto.PatientUpdateDto;
import com.medilab.microserviceback.microservice_client.NoteClient;
import com.medilab.microserviceback.model.Patient;
import com.medilab.microserviceback.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The PatientService is the main class of the microservice-backend-patient that perform business treatments.
 * <p>
 * Implement the PatientUseCase.
 * @see PatientUseCase
 * Requires a PatientRepository class to request the database.
 * @see PatientRepository
 * Requires a ClientName to delete all patient's notes when it needs to be deleted from the database.
 * @see NoteClient
 * </p>
 */
@Service
@AllArgsConstructor
@Slf4j
public class PatientService implements PatientUseCase {
    
    private final PatientRepository repository;
    private final NoteClient client;
    
    @Override
    public List<Patient> getAll() {
        List<Patient> patients = repository.findAll();
        
        log.debug("Patients list size: " + patients.size());
        return patients;
    }
    
    @Override
    public Optional<Patient> getById(String id) {
        return repository.findById(id);
    }
    
    @Override
    public Optional<Patient> getByName(String firstname, String lastname) {
        return repository.findByFirstNameAndLastName(firstname, lastname);
    }
    
    @Override
    public Patient save(PatientSaveDto patientSaveDto) {
        Patient patient = new Patient(patientSaveDto.lastName(), patientSaveDto.firstName(), patientSaveDto.birthDate(),
                patientSaveDto.gender(), patientSaveDto.address(), patientSaveDto.phone());
        
        if(!birthdateFormatValidator(patientSaveDto.birthDate())) {
            throw new RuntimeException();
        }
        return repository.save(patient);
    }
    
    @Override
    public Patient update(PatientUpdateDto patientUpdateDto) {
        
        log.debug("Patients to update: " + patientUpdateDto.firstName() + ", " + patientUpdateDto.lastName() +
                " Patient's informations to update: " + patientUpdateDto.gender() + " - " + patientUpdateDto.address() +
                " - " + patientUpdateDto.phone());
        
        Patient patientFound = getByName(patientUpdateDto.firstName(), patientUpdateDto.lastName()).get();
        
        log.debug(patientFound.getID() + " -  " + patientFound.getFirstName() + " - " + patientFound.getLastName() +
                " - " + patientFound.getAddress() + " - " + patientFound.getGender() + " - " +
                patientFound.getBirthDate() + " - " + patientFound.getPhone());
        
        if(patientUpdateDto.gender() != null) {
            patientFound.setGender(patientUpdateDto.gender());
        }
        
        if(patientUpdateDto.address() != null) {
            patientFound.setAddress(patientUpdateDto.address());
        }
        
        if(patientUpdateDto.phone() != null) {
            patientFound.setPhone(patientUpdateDto.phone());
        }
        
        return repository.save(patientFound);
    }
    
    /**
     * Method to delete a patient and all their notes.
     * Required the NoteClient to delete all notes of the patient.
     *
     * @param id - String - Patient'id.
     */
    @Override
    public void deleteById(String id) {
        
        log.debug("Patient's id to delete: " + id);
        repository.deleteById(id);
        client.deleteNotesByPatientId(id);
    }
    
    /**
     * Method to verify the format of the birthdate before call the repository to save a new patient.
     * Format: yyyy-MM-dd who yyyy should be between 19yy and 202y.
     *
     * @param birthdate - String - the birthdate of the NewPatientDto.
     * @return - Boolean - true if the format is respected and false if is not.
     */
    private Boolean birthdateFormatValidator(String birthdate) {
        return birthdate.matches(
                "(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))");
    }
}
