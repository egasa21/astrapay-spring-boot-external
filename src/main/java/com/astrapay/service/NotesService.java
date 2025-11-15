package com.astrapay.service;

import com.astrapay.dto.NoteDto;
import com.astrapay.dto.CreateNoteRequestDto;
import com.astrapay.repository.NotesRepository;
import com.astrapay.service.model.NoteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotesService {
    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public NoteDto createNote(CreateNoteRequestDto req) {
        NoteModel model = mapToModel(req);
        NoteModel saved = notesRepository.save(model);
        return mapToDto(saved);
    }

    public List<NoteDto> getAllNotes() {
        return notesRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private NoteDto mapToDto(NoteModel model) {
        NoteDto dto = new NoteDto();
        dto.setId(model.getId());
        dto.setContent(model.getContent());
        dto.setCreatedAt(model.getCreatedAt());
        return dto;
    }

    private NoteModel mapToModel(CreateNoteRequestDto dto) {
        return NoteModel.builder()
                .content(dto.getContent())
                .build();
    }
}