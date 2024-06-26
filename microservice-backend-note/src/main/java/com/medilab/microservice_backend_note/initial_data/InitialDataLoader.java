package com.medilab.microservice_backend_note.initial_data;

import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * The InitialDataLoader check if all data are already present in the database. If not, saves them.
 * <p>
 * Requires a NoteRepository to check the data's presence and to save data.
 *
 * @see NoteRepository
 * </p>
 * @see Note
 */
@Component
@Slf4j
public class InitialDataLoader {
    
    private final NoteRepository noteRepository;
    
    public InitialDataLoader(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    
    Note note1 = new Note("dce39adb-2221-4f82-b37e-d801e16e073c", "73052162-5644-47c6-a76e-a3e6ee17eedb",
            "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé",
            LocalDateTime.now());
    Note note2 = new Note("aaf77e48-df7d-4bc0-a5af-57c95da9010d", "90229de5-9024-4cdd-8dec-f7c54669b336",
            "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est Anormale dernièrement",
            LocalDateTime.now());
    Note note3 = new Note("39dbeca5-74bc-44ed-973b-03a197d852b4", "90229de5-9024-4cdd-8dec-f7c54669b336",
            "Le patient déclare avoir fait une Réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être Anormale",
            LocalDateTime.now());
    Note note4 = new Note("a98867f3-e962-4058-a0c2-f07a8fddbd96", "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd",
            "Le patient déclare qu'il Fume depuis peu", LocalDateTime.now());
    Note note5 = new Note("44ede411-e853-4769-9528-792dab40a18f", "7e9ad759-f6da-4474-b8b8-0e3a37bbe6fd",
            "Le patient déclare qu'il est Fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire Anormales Tests de laboratoire indiquant un taux de Cholestérol LDL élevé",
            LocalDateTime.now());
    Note note6 = new Note("2945adc7-894b-4d0c-897f-672a3e543dfc", "d341589d-906c-4d67-8f24-d3db885efe9b",
            "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments",
            LocalDateTime.now());
    Note note7 = new Note("5b595c79-6f6b-490b-b98a-00319f4f5afb", "d341589d-906c-4d67-8f24-d3db885efe9b",
            "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps", LocalDateTime.now());
    Note note8 = new Note("a9442622-b367-4fe7-9cb5-d5e41312dcde", "d341589d-906c-4d67-8f24-d3db885efe9b",
            "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé",
            LocalDateTime.now());
    Note note9 = new Note("9c77c96c-4228-4abd-9fc2-83763a6e8a95", "d341589d-906c-4d67-8f24-d3db885efe9b",
            "Taille, Poids, Cholestérol, Vertige et Réaction", LocalDateTime.now());
    
    /**
     * Event that will be executed at runtime.
     * Launch the verification and save data if it's required.
     *
     * @param event - the event.
     * @see #dataCheck()
     * @see #injectData()
     */
    @EventListener
    public void initialDataLoader(ApplicationReadyEvent event) {
        
        log.info("InitialDataLoader initialize...");
        log.info("Checking the presence of data in progress... ");
        
        dataCheck();
        
        if(dataCheck()) {
            log.info("All data are not found.");
            log.info("Inject data in progress...");
            injectData();
            log.info("Data injected.");
        } else {
            log.info("Data is already present");
        }
        log.info("InitialDataLoader ended");
    }
    
    /**
     * Method called by the initialDataLoader to verify if all data is present in the database.
     *
     * @return boolean - true if all data are present, false if is not.
     * @see #initialDataLoader(ApplicationReadyEvent)
     */
    private boolean dataCheck() {
        if(noteRepository.findById(note1.getId())
                .isEmpty() || noteRepository.findById(note1.getId())
                .isEmpty() || noteRepository.findById(note2.getId())
                .isEmpty() || noteRepository.findById(note3.getId())
                .isEmpty() || noteRepository.findById(note4.getId())
                .isEmpty() || noteRepository.findById(note5.getId())
                .isEmpty() || noteRepository.findById(note6.getId())
                .isEmpty() || noteRepository.findById(note7.getId())
                .isEmpty() || noteRepository.findById(note8.getId())
                .isEmpty() || noteRepository.findById(note9.getId())
                .isEmpty()) {
            return true;
        } else
            return false;
    }
    
    /**
     * Method called by the initialDataLoader to save data in the database.
     *
     * @see #initialDataLoader(ApplicationReadyEvent)
     * @see NoteRepository#save(Note)
     */
    private void injectData() {
        noteRepository.save(note1);
        noteRepository.save(note2);
        noteRepository.save(note3);
        noteRepository.save(note4);
        noteRepository.save(note5);
        noteRepository.save(note6);
        noteRepository.save(note7);
        noteRepository.save(note8);
        noteRepository.save(note9);
    }
}
