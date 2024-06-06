package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.PatientBean;

/**
 * Interface to give mandatory methods to the PatientClientImpl class.
 */
public interface PatientClient {
    
    PatientBean getPatientById(String id);
}
