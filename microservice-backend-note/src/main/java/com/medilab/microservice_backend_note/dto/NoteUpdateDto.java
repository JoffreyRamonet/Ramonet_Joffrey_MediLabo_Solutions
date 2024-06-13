package com.medilab.microservice_backend_note.dto;

import jakarta.validation.constraints.NotBlank;
import com.medilab.microservice_backend_note.model.Note;
import com.medilab.microservice_backend_note.controller.NoteController;

/**
 *  * The Data Transfer Object to update a Note.
 * @param id - String - id of the note.
 * @param note
 * @see Note
 * @see NoteController#update(NoteUpdateDto)
 */
public record NoteUpdateDto(@NotBlank String id, @NotBlank String note) {
}
