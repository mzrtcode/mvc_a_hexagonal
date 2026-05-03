package com.habitjournal.habit_journal_api.application;

import com.habitjournal.habit_journal_api.application.exception.DuplicateHabitException;
import com.habitjournal.habit_journal_api.application.exception.HabitNotFoundException;
import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.in.RetrieveHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HabitService implements CreateHabitUseCase, RetrieveHabitUseCase {

    private final HabitRepositoryPort habitRepositoryPort;

    @Override
    public Habit createHabit(Habit habit) {

        habitRepositoryPort.findByName(habit.getName()).ifPresent(
                existing -> {
                    throw new DuplicateHabitException(habit.getName());
                }
        );

        if(!habit.hasValidName()){
            throw new IllegalArgumentException("El nombre del habito no cumple con las reglas de negocio");

        }
        return habitRepositoryPort.save(habit);
    }

    @Override
    public List<Habit> getHabits() {
        return habitRepositoryPort.findAll();
    }

    @Override
    public Habit getHabit(Long id) {
        return habitRepositoryPort.findById(id).orElseThrow(
                () -> new HabitNotFoundException(id)
        );
    }
}
