package com.medilab.microserviceback.repository;

import com.medilab.microserviceback.model.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientRepositoryImpl implements PatientRepository{
    
    private final ClientMySql repository;
    
    public PatientRepositoryImpl(ClientMySql repository) {
        this.repository = repository;
    }
    
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
    
    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
    repository.deleteByFirstNameAndLastName(firstName, lastName);
    }
    
}
