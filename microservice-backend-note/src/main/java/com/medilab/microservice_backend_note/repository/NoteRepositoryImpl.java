package com.medilab.microservice_backend_note.repository;

import com.medilab.microservice_backend_note.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implement the NoteRepository.
 * Forwards requests to ClientMongoDb to query the MongoDb database.
 *
 * @see NoteRepository
 * @see ClientMongoDb
 */
@Repository
@AllArgsConstructor
public class NoteRepositoryImpl implements NoteRepository {
    
    private ClientMongoDb repository;
    
    @Override
    public List<Note> findAll() {
        return repository.findAll();
    }
    
    @Override
    public Optional<Note> findById(String id) {
        return repository.findById(id);
    }
    
    @Override
    public List<Note> findByPatient(String id) {
        return repository.findByPatient(id);
    }
    
    @Override
    public Note save(Note note) {
        return repository.save(note);
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    @Override
    public void deleteByPatient(String id) {
        repository.deleteByPatient(id);
    }
}
