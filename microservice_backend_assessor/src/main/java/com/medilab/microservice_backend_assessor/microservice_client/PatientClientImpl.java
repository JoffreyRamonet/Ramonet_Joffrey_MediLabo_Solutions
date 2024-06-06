package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.PatientBean;
import com.medilab.microservice_backend_assessor.proxy.MicroservicePatientProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Implement the PatientClient.
 * Use MicroservicePatientProxy to query the microservice-backend-patient.
 *
 * @see PatientClient
 * @see MicroservicePatientProxy
 */
@Component
@AllArgsConstructor
public class PatientClientImpl implements PatientClient {
    
    private MicroservicePatientProxy proxy;
    
    @Override
    public PatientBean getPatientById(String id) {
        return proxy.findPatientById(id);
    }
}
