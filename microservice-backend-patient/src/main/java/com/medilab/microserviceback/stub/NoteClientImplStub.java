package com.medilab.microserviceback.stub;

import com.medilab.microserviceback.microservice_client.NoteClient;
import lombok.extern.slf4j.Slf4j;

/**
 * Mock the note client for tests.
 * Mock the delete http request to the microservice_backend_note's controller.
 */
@Slf4j
public class NoteClientImplStub implements NoteClient {
    
    private final DataBaseStub dataBaseStub = new DataBaseStub();
    
    @Override
    public void deleteNotesByPatientId(String id) {
        log.info("Send delete http request to the microservice_backend_note.");
    }
}
