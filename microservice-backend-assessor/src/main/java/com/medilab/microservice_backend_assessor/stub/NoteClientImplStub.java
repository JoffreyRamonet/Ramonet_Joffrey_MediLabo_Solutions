package com.medilab.microservice_backend_assessor.stub;

import com.medilab.microservice_backend_assessor.bean.NoteBean;
import com.medilab.microservice_backend_assessor.microservice_client.NoteClient;

import java.util.List;

/**
 * Mock the note client for tests.
 * Get all data from DataBaseStub and use the List<NoteBean> like the proxy.
 */
public class NoteClientImplStub implements NoteClient {
    
    private final DataBaseStub dataBaseStub = new DataBaseStub();
    private final List<NoteBean> notes = dataBaseStub.getNotes();
    
    @Override
    public List<NoteBean> getNotesByPatientId(String id) {
        return notes.stream()
                .filter(note -> note.patient()
                        .equals(id))
                .toList();
    }
}
