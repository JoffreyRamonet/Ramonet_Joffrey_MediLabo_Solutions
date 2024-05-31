package com.medilab.microservice_backend_note.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteUpdateDto(@NotBlank String id, @NotBlank String note) {
}
