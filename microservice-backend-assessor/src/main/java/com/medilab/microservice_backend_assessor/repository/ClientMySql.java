package com.medilab.microservice_backend_assessor.repository;

import com.medilab.microservice_backend_assessor.model.Trigger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The repository for the MySql database.
 * Extends the JpaRepository interface.
 *
 * @see JpaRepository
 */
public interface ClientMySql extends JpaRepository<Trigger, String> {
}
