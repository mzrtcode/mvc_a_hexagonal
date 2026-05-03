package com.habitjournal.habit_journal_api.infrastructure.web.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record HabitResponseDTO(
    String id,
    String name,
    List<LocalDateTime> logs
) { }
