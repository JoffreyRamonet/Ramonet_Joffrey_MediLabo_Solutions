package com.medilab.microservice_backend_assessor.bean;

/**
 * PatientBean store the json of the Patient model get from the microservice-backend-patient.
 *
 * @param id        - String
 * @param lastName  - String
 * @param firstName - String
 * @param birthDate - String
 * @param gender    - String
 * @param address   - String
 * @param phone     - String
 * @see <a href="microservice_backend_patient">microservice_backend_patient</a>
 */
public record PatientBean(String id, String lastName, String firstName, String birthDate, String gender, String address,
                          String phone) {
}
