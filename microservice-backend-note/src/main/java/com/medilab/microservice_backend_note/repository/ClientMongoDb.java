package com.medilab.microservice_backend_note.repository;

import com.medilab.microservice_backend_note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The repository for the MongoDb database.
 * Extends the MongoRepository.
 *
 * @see MongoRepository
 */
public interface ClientMongoDb extends MongoRepository<Note, String> {
    List<Note> findByPatient(String id);
    
    void deleteByPatient(String id);
}
