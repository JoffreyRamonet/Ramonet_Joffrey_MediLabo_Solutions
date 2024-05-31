package com.medilab.microservice_backend_note.controller;

import com.medilab.microservice_backend_note.dto.NoteDto;
import com.medilab.microservice_backend_note.dto.NoteUpdateDto;
import com.medilab.microservice_backend_note.error.DataNotFoundException;
import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.service.NoteUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/microservice_backend_note/v1/note")
public class NoteController {
    
    private final NoteUseCase noteService;
    
    @GetMapping("/all")
    public List<Note> getAll() {
        List<Note> notes = noteService.getAll();
        
        log.debug("Notes list size: " + notes.size());
        return notes;
    }
    
    @GetMapping("/{id}")
    public Note getById(
            @PathVariable
            final String id) {
        Optional<Note> note = noteService.getById(id);
        
        if(note.isEmpty()) {
            throw new DataNotFoundException("Note not found");
        }
        log.debug("Note found: " + note.get()
                .getId());
        return note.get();
    }
    
    @GetMapping("/patient/{id}")
    public List<Note> getByPatient(
            @PathVariable
            final String id) {
        List<Note> notes = noteService.getByPatient(id);
        
        log.debug("Notes list size: " + notes.size());
        
        return notes;
    }
    
    @PostMapping("/save")
    public Note save(
            @RequestBody
            final NoteDto noteDto) {
        log.debug("Notes parsed to save: " + noteDto.patient() + " - " + noteDto.note());
        
        return noteService.save(noteDto);
    }
    
    @PatchMapping("/update")
    public Note update(
            @RequestBody
            final NoteUpdateDto noteUpdateDto) {
        log.debug("Note to update: " + noteUpdateDto.id() + ", note: " + noteUpdateDto.note());
        
        return noteService.update(noteUpdateDto);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteById(
            @PathVariable
            final String id) {
        log.debug(id);
        if(noteService.getById(id)
                .isEmpty()) {
            throw new DataNotFoundException("Note not found");
        } else {
            noteService.deleteById(id);
        }
    }
    
    @DeleteMapping("/patient/delete/{id}")
    public void deleteByPatient(
            @PathVariable
            final String id) {
        log.debug(id);
        
        if(noteService.getByPatient(id)
                .isEmpty()) {
            throw new DataNotFoundException("Notes of patient'id : " + id + " not found");
        } else {
            noteService.deleteByPatient(id);
        }
    }
}
