package com.medilab.microserviceback.repository;

import com.medilab.microserviceback.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ClientMySql extends JpaRepository<Patient, String> {
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
