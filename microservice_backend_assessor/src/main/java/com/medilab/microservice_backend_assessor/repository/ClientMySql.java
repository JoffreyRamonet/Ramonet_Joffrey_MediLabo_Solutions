package com.medilab.microservice_backend_assessor.repository;

import com.medilab.microservice_backend_assessor.model.Trigger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientMySql extends JpaRepository<Trigger, String> {
}
