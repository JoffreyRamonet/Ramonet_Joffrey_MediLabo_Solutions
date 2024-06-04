package com.medilab.microservice_backend_assessor.proxy;

import com.medilab.microservice_backend_assessor.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name= "microservice-backend-note", url = "localhost:9003")
public interface MicroserviceNoteProxy {
    
    @GetMapping(value = "/microservice_backend_note/v1/note/patient/{id}")
    List<NoteBean> findAllNotesByPatientId(@PathVariable("id") String id);
}
