package com.medilab.microserviceback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "patient")
@AllArgsConstructor
public class Patient {
    
    @Id
    @Column(name = "id")
    private final String ID;
    
    @NotBlank(message = "Name is mandatory")
    @Column(name = "lastname")
    private String lastName;
    
    @NotBlank(message = "FirstName is mandatory")
    @Column(name = "firstname")
    private String firstName;
    
    @NotBlank(message = "Birthdate is mandatory")
    @Pattern(regexp = "(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))")
    @Column(name = "birthdate")
    private String birthDate;
    
    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "[MF]")
    private String gender;
    
    private String address;
    
    private String phone;

    public Patient(String lastName, String firstName, String birthDate, String gender, String address,
                   String phone) {
        this.ID = UUID.randomUUID().toString();
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }
    
    public Patient() {
        this.ID = UUID.randomUUID().toString();
    }
}
