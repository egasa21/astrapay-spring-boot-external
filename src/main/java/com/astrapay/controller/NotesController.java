package com.astrapay.controller;

import com.astrapay.dto.NoteDto;
import com.astrapay.dto.CreateNoteRequestDto;
import com.astrapay.dto.response.ApiResponse;
import com.astrapay.service.NotesService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public ResponseEntity<ApiResponse<NoteDto>> create(@Valid @RequestBody CreateNoteRequestDto req) {
        NoteDto note = notesService.createNote(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(note, "Note created successfully"));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<NoteDto>>> getAll() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        notesService.getAllNotes(),
                        "Notes retrieved successfully"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteDto>> getNoteById(@PathVariable String id) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        notesService.getNoteById(id),
                        "Note retrieved successfully"
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteNoteById(@PathVariable String id) {
        notesService.deleteNoteById(id);
        return ResponseEntity.ok(ApiResponse.success("Note deleted successfully", null));
    }

}