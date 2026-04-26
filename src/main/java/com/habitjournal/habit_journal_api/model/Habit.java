package com.habitjournal.habit_journal_api.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Table(name = "habits")
@AllArgsConstructor
@NoArgsConstructor
public class Habit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogEntry> logEntries = new ArrayList<>();

    public void addLogEntry(LogEntry logEntry){
        logEntries.add(logEntry);
        logEntry.setHabit(this);
    }
    public void changeName(String name) {
        this.name = name;
    }

    public List<LogEntry> getLogEntries() {
        return Collections.unmodifiableList(logEntries);
    }

}
