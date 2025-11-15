package com.astrapay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateNoteRequestDto {
    @NotBlank(message = "Note content cannot be empty")
    private String content;

}