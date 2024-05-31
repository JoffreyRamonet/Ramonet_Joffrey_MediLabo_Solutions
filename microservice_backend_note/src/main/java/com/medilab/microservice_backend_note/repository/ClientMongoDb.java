package com.medilab.microservice_backend_note.repository;

import com.medilab.microservice_backend_note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ClientMongoDb extends MongoRepository<Note, String> {
 List<Note> findByPatient(String id);
 void deleteByPatient(String id);
}
