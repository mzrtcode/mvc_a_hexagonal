package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "habits")
public class HabitEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LogEntryEntity> logEntries = new ArrayList<>();

    public void addNewLogEntry(LogEntryEntity logEntryEntity){
        logEntries.add(logEntryEntity);
        logEntryEntity.setHabit(this);
    }
}
