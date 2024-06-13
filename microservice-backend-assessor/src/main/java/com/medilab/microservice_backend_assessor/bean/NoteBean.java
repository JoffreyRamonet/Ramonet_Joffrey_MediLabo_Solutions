package com.medilab.microservice_backend_assessor.bean;

import java.time.LocalDateTime;

/**
 * NoteBean store the json of the Note model get from the microservice-backend-note.
 *
 * @param id        - String
 * @param patient   - String
 * @param note      - String
 * @param createdAt - LocalDateTime
 * @see <a href="microservice_backend_note">microservice_backend_note</a>
 */
public record NoteBean(String id, String patient, String note, LocalDateTime createdAt) {
}
