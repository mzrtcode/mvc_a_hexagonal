package com.habitjournal.habit_journal_api.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record HabitRequestDTO(
        @NotBlank(message = "El nombre del habito no puede estar vacio")
        String name,
        List<LocalDateTime> logs) {
}
