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
           note.setUpdatedAt(Instant.now());
       }
       notes.put(note.getId(), note);
       return note;
    }

    public List<NoteModel> findAll() {
        ArrayList<NoteModel> list = new ArrayList<>(notes.values());
        list.sort(Comparator.comparing(NoteModel::getCreatedAt).reversed());
        return Collections.unmodifiableList(list);
    }

    public Optional<NoteModel> findById(String id) {
        return Optional.ofNullable(notes.get(id));
    }

    public void deleteById(String id) {
        notes.remove(id);
    }

    public NoteModel updateNoteById(String id, NoteModel note) {
        NoteModel saved = notes.get(id);
        saved.setContent(note.getContent());
        saved.setUpdatedAt(Instant.now());
        return saved;
    }
}
