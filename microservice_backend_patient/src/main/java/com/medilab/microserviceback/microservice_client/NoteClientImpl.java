package com.medilab.microserviceback.microservice_client;


import com.medilab.microserviceback.proxy.MicroserviceNoteProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * Implement the NoteClient.
 * Use MicroserviceNoteProxy to query the microservice-backend-note.
 *
 * @see NoteClient
 * @see MicroserviceNoteProxy
 */
@Component
@AllArgsConstructor
public class NoteClientImpl implements NoteClient {
    
    private MicroserviceNoteProxy proxy;
    
    @Override
    public void deleteNotesByPatientId(String id) {
        proxy.deleteAllNotesByPatientId(id);
    }
}
