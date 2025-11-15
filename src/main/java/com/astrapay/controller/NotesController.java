package com.astrapay.controller;

import com.astrapay.dto.NoteDto;
import com.astrapay.dto.CreateNoteRequestDto;
import com.astrapay.service.NotesService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Notes Controller")
@RequestMapping("/api/v1/notes")
@Slf4j
@Validated
public class NotesController {
    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping
    public ResponseEntity<NoteDto> create(@Valid @RequestBody CreateNoteRequestDto req) {
        NoteDto note = notesService.createNote(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping
    public ResponseEntity<Iterable<NoteDto>> getAll() {
        return ResponseEntity.ok(notesService.getAllNotes());
    }

}