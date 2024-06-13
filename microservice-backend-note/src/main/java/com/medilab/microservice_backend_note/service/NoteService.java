package com.medilab.microservice_backend_note.service;

import com.medilab.microservice_backend_note.dto.NoteDto;
import com.medilab.microservice_backend_note.dto.NoteUpdateDto;
import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The NoteService is the main class of the microservice-backend-note that perform business treatments.
 * <p>
 * Implement the NoteUseCase.
 *
 * @see NoteUseCase
 * Requires a NoteRepository class to request the database.
 * @see NoteRepository
 * </p>
 */
@Service
@AllArgsConstructor
@Slf4j
public class NoteService implements NoteUseCase {
    
    private final NoteRepository repository;
    
    @Override
    public List<Note> getAll() {
        
        List<Note> notes = repository.findAll();
        
        log.debug("NoteService - getAll - Notes list size: " + notes.size());
        
        return notes;
    }
    
    @Override
    public Optional<Note> getById(String id) {
        
        Optional<Note> note = repository.findById(id);
        
        log.debug("NoteService - getById - id parsed: " + id + "note is present: " + note.isPresent());
        
        return note;
    }
    
    @Override
    public List<Note> getByPatient(String id) {
        
        List<Note> notes = repository.findByPatient(id);
        
        log.debug("NoteService - getByPatient - id parsed: " + id + "Notes list size: " + notes.size());
        
        return notes;
    }
    
    @Override
    public Note save(NoteDto noteDto) {
        
        Note note = new Note(noteDto.patient(), noteDto.note());
        
        log.debug("NoteService - save - Note created: " + note.getId());
        
        return repository.save(note);
    }
    
    @Override
    public Note update(NoteUpdateDto noteUpdateDto) {
        
        log.debug("NoteService - update - Note to update: " + noteUpdateDto.id() + ", information to update: " +
                noteUpdateDto.note());
        
        Note noteFound = getById(noteUpdateDto.id()).get();
        
        noteFound.setNote(noteUpdateDto.note());
        
        return repository.save(noteFound);
    }
    
    @Override
    public void deleteById(String id) {
        
        log.debug("NoteService - deleteById - Id to delete: " + id);
        
        repository.deleteById(id);
    }
    
    @Override
    public void deleteByPatient(String id) {
        
        log.debug("NoteService - deleteByPatient - Id to delete: " + id);
        
        repository.deleteByPatient(id);
    }
}
