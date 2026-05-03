package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection  = "habits")
public class HabitDocument {

    @Id
    private Long id;
    private String name;

    List<LogEntryDocument> logEntries =  new ArrayList<>();

    public void addNewLogEntry(LogEntryDocument logEntry) {
        logEntries.add(logEntry);
    }
}
