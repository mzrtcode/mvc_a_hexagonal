package com.habitjournal.habit_journal_api.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LogEntry {
    private LocalDateTime entryDate;
}
