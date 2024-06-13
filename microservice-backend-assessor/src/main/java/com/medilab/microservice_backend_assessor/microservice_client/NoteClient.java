package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.NoteBean;

import java.util.List;

/**
 * Interface to give mandatory methods to the NoteClientImpl class.
 */
public interface NoteClient {
    
    List<NoteBean> getNotesByPatientId(String id);
}
