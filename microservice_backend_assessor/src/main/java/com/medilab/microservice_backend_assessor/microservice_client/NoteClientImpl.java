package com.medilab.microservice_backend_assessor.microservice_client;

import com.medilab.microservice_backend_assessor.bean.NoteBean;
import com.medilab.microservice_backend_assessor.proxy.MicroserviceNoteProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NoteClientImpl implements NoteClient{
    
    private MicroserviceNoteProxy proxy;
    
    @Override
    public List<NoteBean> getNotesByPatientId(String id) {
        return proxy.findAllNotesByPatientId(id);
    }
}
