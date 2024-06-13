package com.medilab.microserviceback.dto;

import com.medilab.microserviceback.model.Patient;
import com.medilab.microserviceback.controller.PatientController;

/**
 * The Data Transfer Object to save a new Patient.
 *
 * @param lastName  - String.
 * @param firstName - String.
 * @param birthDate - String - Require a specific format, filtered by the microservice-frontend and checked by the PatientService before call the repository.
 * @param gender    - String - Require a specific format, constraint by the Patient model class.
 * @param address   - String.
 * @param phone     - String.
 * @see Patient
 * @see PatientController#save(PatientSaveDto)
 */
public record PatientSaveDto(String lastName, String firstName, String birthDate, String gender, String address,
                             String phone) {
}
