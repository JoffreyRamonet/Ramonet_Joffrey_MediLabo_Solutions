package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.PatientBean;

public interface PatientClient {
    
    PatientBean getPatientById(String id);
}
