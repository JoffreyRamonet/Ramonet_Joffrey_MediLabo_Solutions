package com.medilab.microserviceback.repository;

import com.medilab.microserviceback.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implement the PatientRepository.
 * Forwards requests to ClientMySql to query the MySQL database.
 *
 * @see PatientRepository
 * @see ClientMySql
 */
@Repository
@AllArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {
    
    
    private final ClientMySql repository;
    
    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }
    
    @Override
    public Optional<Patient> findById(String id) {
        return repository.findById(id);
    }
    
    @Override
    public Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }
    
    @Override
    public Patient save(Patient patient) {
        return repository.save(patient);
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
