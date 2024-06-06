package com.medilab.microservice_backend_assessor.proxy;

import com.medilab.microservice_backend_assessor.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * MicroserviceNoteProxy is the FeignClient of the microservice-backend-note.
 * Get all note of a patient and return a List<NoteBean>
 */
@FeignClient(
        name = "microservice-backend-note",
        url = "localhost:9003")
public interface MicroserviceNoteProxy {
    
    /**
     * Call the getByPatient method of the microservice-backend-note's controller to get all notes of a patient.
     *
     * @param id - String - patient's id
     * @return - List<NoteBean>
     */
    @GetMapping(value = "/microservice_backend_note/v1/note/patient/{id}")
    List<NoteBean> findAllNotesByPatientId(
            @PathVariable("id") String id);
}
