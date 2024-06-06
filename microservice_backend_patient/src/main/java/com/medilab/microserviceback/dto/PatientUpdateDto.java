package com.medilab.microserviceback.dto;

import com.medilab.microserviceback.model.Patient;
import com.medilab.microserviceback.controller.PatientController;

/**
 * The Data Transfer Object to update a Patient.
 *
 * @param lastName -String.
 * @param firstName -String.
 * @param gender -String - Require a specific format, constraint by the Patient model class.
 * @param address -String.
 * @param phone -String.
 * @see Patient
 * @see PatientController#update(PatientUpdateDto)
 */
public record PatientUpdateDto(String lastName, String firstName, String gender, String address, String phone) {

}
