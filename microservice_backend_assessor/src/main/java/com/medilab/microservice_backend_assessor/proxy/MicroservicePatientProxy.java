package com.medilab.microservice_backend_assessor.proxy;

import com.medilab.microservice_backend_assessor.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * MicroservicePatientProxy is the FeignClient of the microservice-backend-patient.
 * Get all note of a patient and return a List<NoteBean>
 */
@FeignClient(
        name = "microservice-backend-patient",
        url = "localhost:9001")
public interface MicroservicePatientProxy {
    
    /**
     * Call the getById method of the microservice-backend-patient's controller to get data of a patient.
     *
     * @param id - String - patient's id
     * @return - PatientBean
     */
    @GetMapping(value = "/microservice_backend_patient/v1/patient/{id}")
    PatientBean findPatientById(
            @PathVariable("id") String id);
}
