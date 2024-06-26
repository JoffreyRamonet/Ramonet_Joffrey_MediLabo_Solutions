package com.medilab.microservice_backend_note.service;

import com.medilab.microservice_backend_note.dto.NoteDto;
import com.medilab.microservice_backend_note.dto.NoteUpdateDto;
import com.medilab.microservice_backend_note.model.Note;

import java.util.List;
import java.util.Optional;

/**
 * Interface to give mandatory methods to the service class.
 */
public interface NoteUseCase {
    List<Note> getAll();
    
    Optional<Note> getById(String id);
    
    List<Note> getByPatient(String id);
    
    Note save(NoteDto noteDto);
    
    Note update(NoteUpdateDto noteUpdateDto);
    
    void deleteById(String id);
    
    void deleteByPatient(String id);
}
