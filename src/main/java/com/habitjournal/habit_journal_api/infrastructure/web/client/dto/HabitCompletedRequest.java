package com.habitjournal.habit_journal_api.infrastructure.web.client.dto;

public record HabitCompletedRequest(Long userId,
                                    String habitId
) { }
