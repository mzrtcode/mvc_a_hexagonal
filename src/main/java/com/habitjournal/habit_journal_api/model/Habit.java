package com.habitjournal.habit_journal_api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "habits")
@Data
public class Habit {

    @Id
    private String id;
    private String name;

    private List<LogEntry> logEntries = new ArrayList<>();

}
