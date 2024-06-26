package com.medilab.microservice_backend_note.dto;

import jakarta.validation.constraints.NotBlank;
import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.controller.NoteController;

/**
 * The Data Transfer Object to save a new Note.
 *
 * @param patient - String - id of the Patient.
 * @param note    - String
 * @see Note
 * @see NoteController#save(NoteDto)
 */
public record NoteDto(@NotBlank String patient, @NotBlank String note) {
}
