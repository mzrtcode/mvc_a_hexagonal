package com.habitjournal.habit_journal_api.repository;

import com.habitjournal.habit_journal_api.model.Habit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    @EntityGraph(attributePaths = "logEntries")
    @Query("SELECT h FROM Habit h")
    List<Habit> findAllWithLogs();

    Optional<Habit> findHabitByName(String name);
}
