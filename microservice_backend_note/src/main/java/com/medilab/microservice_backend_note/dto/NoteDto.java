package com.medilab.microservice_backend_note.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteDto(@NotBlank String patient, @NotBlank String note) {
}
