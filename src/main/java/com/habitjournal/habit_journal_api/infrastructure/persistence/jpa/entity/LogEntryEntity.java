package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "log_entries")
public class LogEntryEntity {
    @Id @UuidGenerator
    private String id;
    private LocalDateTime entryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private HabitEntity habit;
}
