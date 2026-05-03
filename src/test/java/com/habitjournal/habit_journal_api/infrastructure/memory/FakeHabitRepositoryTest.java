package com.habitjournal.habit_journal_api.infrastructure.memory;

import com.habitjournal.habit_journal_api.application.HabitService;
import com.habitjournal.habit_journal_api.application.exception.DuplicateHabitException;
import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.LogEntry;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeHabitRepositoryTest {

    private HabitService habitService;
    private HabitRepositoryPort habitRepositoryPort;


    @BeforeEach
    void setUp() {
        habitRepositoryPort = new FakeHabitRepository();
        habitService = new HabitService(habitRepositoryPort);
    }

    @Test
    @DisplayName("Debe crear y recuperar un habito correctamente (Happy Path)")
    void getHabitById() {
        //Arrange
        Habit newHabit = new Habit();
        newHabit.setName("Leer Clean Code");

        //Act
        Habit savedHabit = habitService.createHabit(newHabit);

        //Assert
        assertNotNull(savedHabit.getId(), "El ID no deberia ser nulo despues de guardar");
        assertFalse(savedHabit.getId().isEmpty());
        assertEquals("Leer Clean Code", savedHabit.getName());

        Habit foundHabit = habitService.getHabit(savedHabit.getId());
        assertEquals(savedHabit.getId(), foundHabit.getId());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre está duplicado")
    void shouldThrowExceptionForDuplicateName() {
        // GIVEN
        Habit habit1 = new Habit();
        habit1.setName("Correr");
        habitService.createHabit(habit1);

        Habit habit2 = new Habit();
        habit2.setName("Correr"); // Nombre duplicado

        // WHEN & THEN
        assertThrows(DuplicateHabitException.class, () -> {
            habitService.createHabit(habit2);
        });
    }

    @Test
    @DisplayName("Debe persistir los LogEntries correctamente")
    void shouldPersistLogEntries() {
        // GIVEN
        Habit habit = new Habit();
        habit.setName("Meditar");

        LogEntry log1 = new LogEntry();
        log1.setEntryDate(LocalDateTime.now());

        habit.setLogEntries(List.of(log1));

        // WHEN
        Habit savedHabit = habitService.createHabit(habit);

        // THEN
        Habit retrievedHabit = habitService.getHabit(savedHabit.getId());
        assertEquals(1, retrievedHabit.getLogEntries().size());
    }
}