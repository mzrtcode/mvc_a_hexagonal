package com.habitjournal.habit_journal_api.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Habit {
    private String id;
    private String name;
    private List<LogEntry> logEntries = new ArrayList<>();

    public boolean hasValidName(){
        return this.name  != null && this.name.trim().length() >= 5;
    }

    public void addNewLogEntry(LogEntry newLog){
        logEntries.add(newLog);
    }
}
