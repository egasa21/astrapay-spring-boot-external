package com.astrapay.repository;

import com.astrapay.service.model.NoteModel;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NotesRepository {
    private final ConcurrentHashMap<String, NoteModel> notes = new ConcurrentHashMap<>();

    public NoteModel save(NoteModel note) {
       if(note.getId() == null){
           note.setId(UUID.randomUUID().toString());
           note.setCreatedAt(Instant.now());
       }
       notes.put(note.getId(), note);
       return note;
    }
}
