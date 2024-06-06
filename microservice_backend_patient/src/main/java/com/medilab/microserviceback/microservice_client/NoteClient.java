package com.medilab.microserviceback.microservice_client;

/**
 * Interface to give mandatory methods to the NoteClientImpl class.
 */
public interface NoteClient {
    
    void deleteNotesByPatientId(String id);
}
