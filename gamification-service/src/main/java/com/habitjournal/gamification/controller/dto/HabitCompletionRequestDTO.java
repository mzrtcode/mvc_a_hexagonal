package com.habitjournal.gamification.controller.dto;

import java.time.LocalDateTime;

public record HabitCompletionRequestDTO(Long userId,
                                        String habitId
) { }
