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

@Service
@AllArgsConstructor
@Slf4j
public class NoteService implements NoteUseCase {
    
    private final NoteRepository repository;
    
    @Override
    public List<Note> getAll() {
        List<Note> notes = repository.findAll();
        
        log.debug("Notes list size: " + notes.size());
        
        return notes;
    }
    
    @Override
    public Optional<Note> getById(String id) {
        return repository.findById(id);
    }
    
    @Override
    public List<Note> getByPatient(String id) {
        List<Note> notes = repository.findByPatient(id);
        
        log.debug("Notes list size: " + notes.size());
        
        return notes;
    }
    
    @Override
    public Note save(NoteDto noteDto) {
        Note note = new Note(noteDto.patient(), noteDto.note());
        return repository.save(note);
    }
    
    @Override
    public Note update(NoteUpdateDto noteUpdateDto) {
        
        log.debug("Note to update: " + noteUpdateDto.id() + ", information to update: " + noteUpdateDto.note());
        
        Note noteFound = getById(noteUpdateDto.id()).get();
        
        noteFound.setNote(noteUpdateDto.note());
        
        return repository.save(noteFound);
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
