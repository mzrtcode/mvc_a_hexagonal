package com.habitjournal.habit_journal_api.domain.ports.in;

import com.habitjournal.habit_journal_api.domain.Habit;

import java.util.List;

public interface RetrieveHabitUseCase {
    List<Habit> getHabits();
    Habit getHabit(Long id);
}
