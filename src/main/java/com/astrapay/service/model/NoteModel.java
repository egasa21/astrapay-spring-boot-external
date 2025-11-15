package com.astrapay.service.model;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {
    private String id;
    private String content;
    private Instant createdAt;

}
