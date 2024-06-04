package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.NoteBean;

import java.util.List;

public interface NoteClient {
    
    List<NoteBean> getNotesByPatientId(String id);
}
