package com.medilab.microservice_backend_assessor.bean;

import java.time.LocalDateTime;

public record NoteBean(String id, String patient, String note, LocalDateTime createdAt) {
}
