package com.medilab.microservice_backend_assessor.bean;


public record PatientBean(String id, String lastName, String firstName, String birthDate, String gender, String address, String phone) {
}
