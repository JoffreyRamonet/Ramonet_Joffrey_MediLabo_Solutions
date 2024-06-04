package com.medilab.microservice_backend_assessor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "triggers")
public class Trigger {
    
    @Id
    private String id;
    private String name;
    
    
    public Trigger(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
