package com.medilab.microserviceback.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * MicroserviceNoteProxy is the FeignClient of the microservice-backend-note.
 * Delete all note of a patient when the patient is deleted.
 */
@FeignClient(
        name = "microservice-backend-note")
public interface MicroserviceNoteProxy {
    
    /**
     * Call the deleteByPatient method of the microservice-backend-note's controller to delete all notes of a patient.
     *
     * @param id - String - patient's id
     */
    @DeleteMapping(value = "/microservice-backend-note/v1/note/patient/delete/{id}")
    void deleteAllNotesByPatientId(
            @PathVariable("id") String id);
}
