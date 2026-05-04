package com.habitjournal.habit_journal_api.domain.ports.out;

public interface GamificationPort {
    void notifyHabitCreation(Long userId, String habitId);

}
