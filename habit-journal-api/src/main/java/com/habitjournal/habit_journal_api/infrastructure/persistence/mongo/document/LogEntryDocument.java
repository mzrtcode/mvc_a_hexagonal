package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogEntryDocument {
    private LocalDateTime entryDate;
}
