package com.medilab.microservice_backend_assessor.service;

import com.medilab.microservice_backend_assessor.bean.NoteBean;
import com.medilab.microservice_backend_assessor.microservice_client.NoteClient;
import com.medilab.microservice_backend_assessor.model.Trigger;
import com.medilab.microservice_backend_assessor.repository.TriggerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TriggerCounter service expose only one method that return the number of trigger present in the patient notes.
 * Requires a NoteClient to get the list<NoteBean> of the patient.
 * @see NoteClient
 * Requires a TriggerRepository to get the list<Trigger>
 * @see TriggerRepository
 */
@Service
@AllArgsConstructor
@Slf4j
public class TriggerCounter {
    
    private NoteClient client;
    private TriggerRepository repository;
    
    /**
     * Method to count the number of triggers presence in the patient notes.
     * @param id - String - Patient's id.
     * @return - Integer - The number of triggers.
     * @see TriggerRepository#findAll()
     */
    public Integer triggerCounter(String id) {
        
        List<String> all = repository.findAll()
                .stream()
                .map(Trigger::getName)
                .toList();
        
        log.debug("TriggerCounter - triggerCounter - list of trigger: " + all);
        
        List<String> allNotes = getAllNotes(id);
        
        log.debug("TriggerCounter - triggerCounter - list of note: " + allNotes);
        
        Integer counter = 0;
        
        log.debug("TriggerCounter - triggerCounter - identified trigger: ");
        for(String trigger : all) {
            for(String note : allNotes) {
                if(trigger.equals(note)) {
                    counter++;
                    log.debug(trigger);
                }
            }
        }
        log.debug("TriggerCounter - triggerCounter - counter: " + counter);
        return counter;
    }
    
    /**
     * Method to format all NoteBean that returned by the NoteClient in an Array of String.
     *
     * Check if Hémoglobine and A1C next and concatenate to be counted in the calculation.
     * @param id - String - Patient's id
     * @return - List<String>
     * @see NoteClient#getNotesByPatientId(String)
     */
    private List<String> getAllNotes(String id) {
        
        String notesString = client.getNotesByPatientId(id)
                .stream()
                .map(NoteBean::note)
                .collect(Collectors.joining(" "));
        
        log.debug("TriggerCounter - getAllNotes - string formed from the note list: " + notesString);
        
        notesString = notesString.replaceAll("\\p{Punct}", " ");
        
        List<String> list = new ArrayList<>(Arrays.asList(notesString.split(" ")));
        
        for(int i = 1; i <= list.size() - 1; i++) {
            
            if(list.get(i)
                    .equals("A1C") && list.get(i - 1)
                    .equals("Hémoglobine")) {
                list.set(i, "Hémoglobine A1C");
                list.remove(i - 1);
                log.debug("TriggerCounter - getAllNotes - Hémoglobine A1C found");
            }
        }
        log.debug("TriggerCounter - getAllNotes - note list: " + list);
        return list;
    }
}
