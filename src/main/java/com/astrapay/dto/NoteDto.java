package com.astrapay.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class NoteDto {
    private String id;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
}
