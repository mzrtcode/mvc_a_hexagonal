package com.habitjournal.gamification.controller.dto;

import java.time.LocalDateTime;

public record HabitCompletionResponseDTO(Long userId,
                                         String habitId,
                                         Integer points,
                                         LocalDateTime awardedAt
) { }
