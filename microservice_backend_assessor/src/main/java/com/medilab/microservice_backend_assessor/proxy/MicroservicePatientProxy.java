package com.medilab.microservice_backend_assessor.proxy;

import com.medilab.microservice_backend_assessor.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-backend-patient", url = "localhost:9001")
public interface MicroservicePatientProxy {
    
    @GetMapping(value = "/microservice_backend_patient/v1/patient/{id}")
    PatientBean findPatientById(@PathVariable("id") String id);
}
