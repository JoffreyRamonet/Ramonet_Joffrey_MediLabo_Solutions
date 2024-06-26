package com.medilab.microserviceback.repository;

import com.medilab.microserviceback.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The repository for the MySql database.
 * Extends the JpaRepository interface.
 *
 * @see JpaRepository
 */
public interface ClientMySql extends JpaRepository<Patient, String> {
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
