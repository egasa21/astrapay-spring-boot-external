package com.astrapay.repository;

import com.astrapay.service.model.NoteModel;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
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

    public List<NoteModel> findAll() {
        ArrayList<NoteModel> list = new ArrayList<>(notes.values());
        list.sort(Comparator.comparing(NoteModel::getCreatedAt).reversed());
        return Collections.unmodifiableList(list);
    }
}
