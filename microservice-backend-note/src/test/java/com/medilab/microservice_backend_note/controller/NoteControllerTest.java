package com.medilab.microservice_backend_note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilab.microservice_backend_note.dto.NoteDto;
import com.medilab.microservice_backend_note.dto.NoteUpdateDto;
import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.service.NoteService;
import com.medilab.microservice_backend_note.stub.NoteRepositoryImplStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NoteControllerTest {
    
    private final NoteRepositoryImplStub repository = new NoteRepositoryImplStub();
    private final NoteService service = new NoteService(repository);
    
    @InjectMocks
    private final NoteController controller = new NoteController(service);
    
    private MockMvc mvc;
    private ObjectMapper objectMapper;
    
    private final String URL = "/microservice-backend-note/v1/note";
    
    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
        this.objectMapper = new ObjectMapper();
    }
    
    @Test
    void getAllShouldReturnAllNoteTest() throws Exception {
        int size = repository.findAll()
                .size();
        
        mvc.perform(get(URL + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(size))
                .andDo(print());
    }
    
    @Test
    void getByIdShouldReturnTheCorrectNoteTest() throws Exception {
        String id = "39dbeca5-74bc-44ed-973b-03a197d852b4";
        Note noteFound = repository.findById(id)
                .get();
        
        mvc.perform(get(URL + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.note").value(noteFound.getNote()))
                .andDo(print());
    }
    
    @Test
    void getByPatientShouldReturnTheListOfAllPatientNoteTest() throws Exception {
        String id = "d341589d-906c-4d67-8f24-d3db885efe9b";
        int size = repository.findByPatient(id)
                .size();
        
        mvc.perform(get(URL + "/patient/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(size))
                .andDo(print());
    }
    
    @Test
    void saveShouldSaveANewNoteTest() throws Exception {
        NoteDto noteDto = new NoteDto("d341589d-906c-4d67-8f24-d3db885efe9b", "test");
        
        mvc.perform(post(URL + "/save").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.note").value(noteDto.note()))
                .andDo(print());
    }
    
    @Test
    void updateShouldUpdateANoteTest() throws Exception {
        NoteUpdateDto noteUpdateDto = new NoteUpdateDto("9c77c96c-4228-4abd-9fc2-83763a6e8a95",
                "Taille, Poids, Cholestérol, Vertige et Réaction et plus encore");
        
        mvc.perform(patch(URL + "/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noteUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.note").value(noteUpdateDto.note()))
                .andDo(print());
    }
    
    @Test
    void deleteByIdTest() throws Exception{
        Note note = new Note("test", "test");
        repository.save(note);
        
        mvc.perform(delete(URL + "/delete/{id}", note.getId()))
                .andExpect(status().isOk());
    }
    
    @Test
    void deleteByPatientTest() throws Exception{
        Note note = new Note("test", "test");
        repository.save(note);
        
        mvc.perform(delete(URL + "/patient/delete/{id}", note.getPatient()))
                .andExpect(status().isOk());
    }
}
