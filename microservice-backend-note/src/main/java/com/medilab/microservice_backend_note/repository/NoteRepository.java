package com.medilab.microservice_backend_note.repository;

import com.medilab.microservice_backend_note.model.Note;

import java.util.List;
import java.util.Optional;

/**
 * Interface to give mandatory methods to repository classes.
 */
public interface NoteRepository {
    List<Note> findAll();
    
    Optional<Note> findById(String id);
    
    List<Note> findByPatient(String id);
    
    Note save(Note note);
    
    void deleteById(String id);
    
    void deleteByPatient(String id);
}
