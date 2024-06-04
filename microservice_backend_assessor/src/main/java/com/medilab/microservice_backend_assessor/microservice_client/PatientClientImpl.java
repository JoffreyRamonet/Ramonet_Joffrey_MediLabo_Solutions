package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.PatientBean;
import com.medilab.microservice_backend_assessor.proxy.MicroservicePatientProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientClientImpl implements PatientClient{
    
    private MicroservicePatientProxy proxy;
    
    @Override
    public PatientBean getPatientById(String id) {
        return proxy.findPatientById(id);
    }
}
