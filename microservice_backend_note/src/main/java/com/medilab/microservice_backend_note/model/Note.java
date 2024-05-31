package com.medilab.microservice_backend_note.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "notes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    private String id;
    private String patient;
    private String note;
    private LocalDateTime createdAt;
    
    public Note(String patient, String note){
        this.id = UUID.randomUUID().toString();
        this.patient = patient;
        this.note = note;
        this.createdAt = LocalDateTime.now();
    }
}
