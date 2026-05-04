package com.habitjournal.habit_journal_api.domain.ports.in;

import com.habitjournal.habit_journal_api.domain.Habit;

public interface CreateHabitUseCase {
    Habit createHabit(Habit habit);

}
