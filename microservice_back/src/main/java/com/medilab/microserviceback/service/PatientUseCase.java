package com.medilab.microserviceback.service;

import com.medilab.microserviceback.dto.PatientSaveDto;
import com.medilab.microserviceback.dto.PatientUpdateDto;
import com.medilab.microserviceback.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientUseCase {
    List<Patient> getAll();
    Optional<Patient> getById(String id);
    Optional<Patient> getByName(String firstname, String lastname);
    Patient save(PatientSaveDto patientSaveDto);
    Patient update(PatientUpdateDto patientUpdateDto);
    void deleteById(String id);
    void deleteByName(String firstname, String lastname);
}
