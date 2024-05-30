package com.medilab.microserviceback.dto;

public record PatientSaveDto(String lastName, String firstName, String birthDate, String gender, String address, String phone) {
}
