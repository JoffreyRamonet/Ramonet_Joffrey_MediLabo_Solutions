package com.medilab.microservice_backend_note.service;

import com.medilab.microservice_backend_note.dto.NoteDto;
import com.medilab.microservice_backend_note.dto.NoteUpdateDto;
import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.stub.NoteRepositoryImplStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoteServiceTest {
    
    private final NoteRepositoryImplStub repository = new NoteRepositoryImplStub();
    private final NoteService service = new NoteService(repository);
    
    @Test
    void getAllShouldReturnAllNotesTest() {
        int size = repository.findAll()
                .size();
        
        List<Note> result = service.getAll();
        
        assertEquals(size, result.size());
    }
    
    @Test
    void getByIdShouldReturnANoteByIdTest() {
        String id = "39dbeca5-74bc-44ed-973b-03a197d852b4";
        
        Note noteFound = repository.findById(id)
                .get();
        
        Note result = service.getById(id)
                .get();
        
        assertEquals(noteFound, result);
    }
    
    @Test
    void getByPatientShouldReturnTheNoteListByPatientIdTest() {
        String id = "d341589d-906c-4d67-8f24-d3db885efe9b";
        
        int size = repository.findByPatient(id)
                .size();
        
        List<Note> result = service.getByPatient(id);
        
        assertEquals(size, result.size());
    }
    
    @Test
    void saveShouldSaveANewNoteTest() {
        NoteDto noteDto = new NoteDto("d341589d-906c-4d67-8f24-d3db885efe9b", "test");
        
        Note result = service.save(noteDto);
        
        assertEquals(noteDto.note(), result.getNote());
    }
    
    @Test
    void updateShouldUpdateANoteTest() {
        NoteUpdateDto noteUpdateDto = new NoteUpdateDto("9c77c96c-4228-4abd-9fc2-83763a6e8a95",
                "Taille, Poids, Cholestérol, Vertige et Réaction, et plus encore.");
        
        Note result = service.update(noteUpdateDto);
        
        assertEquals(noteUpdateDto.note(), result.getNote());
    }
    
    @Test
    void deleteByIdShouldDeleteTheNoteTest() {
        Note note = new Note("test", "test");
        repository.save(note);
        
        assertDoesNotThrow(() -> service.deleteById(note.getId()));
    }
    
    @Test
    void deleteByPatientShouldDeleteAllNotesOfAPatientTest() {
        Note note = new Note("test", "test");
        Note note2 = new Note("test", "test");
        repository.save(note);
        repository.save(note2);
        
        assertDoesNotThrow(() -> service.deleteByPatient(note.getPatient()));
    }
}
