package com.medilab.microservice_backend_note.stub;

import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.repository.NoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Mock the repository for tests.
 * Get all data from DataBaseStub and use the List<Note> like the database.
 */
public class NoteRepositoryImplStub implements NoteRepository {
    
    private final DataBaseStub data = new DataBaseStub();
    private final List<Note> notes = new CopyOnWriteArrayList<>(data.getNotes());
    
    
    @Override
    public List<Note> findAll() {
        return notes;
    }
    
    @Override
    public Optional<Note> findById(String id) {
        return notes.stream()
                .filter(note -> note.getId()
                        .equals(id))
                .findFirst();
    }
    
    @Override
    public List<Note> findByPatient(String id) {
        return notes.stream()
                .filter(note -> note.getPatient()
                        .equals(id))
                .toList();
    }
    
    @Override
    public Note save(Note note) {
        notes.add(note);
        return notes.stream()
                .filter(saved -> saved.getId()
                        .equals(note.getId()))
                .findFirst()
                .get();
    }
    
    @Override
    public void deleteById(String id) {
        notes.removeIf(note -> note.getId()
                .equals(id));
    }
    
    @Override
    public void deleteByPatient(String id) {
        notes.removeIf(note -> note.getPatient()
                .equals(id));
    }
}
