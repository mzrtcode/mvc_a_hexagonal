package com.habitjournal.habit_journal_api.domain.ports.out;

import com.habitjournal.habit_journal_api.domain.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitRepositoryPort {
    Habit save(Habit habit);
    Optional<Habit> findByName(String name);
    List<Habit> findAll();
    Optional<Habit> findById(String id);
}
