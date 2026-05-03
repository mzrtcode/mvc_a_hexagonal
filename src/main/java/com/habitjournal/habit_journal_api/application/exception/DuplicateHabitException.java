package com.habitjournal.habit_journal_api.application.exception;

public class DuplicateHabitException extends RuntimeException {
    public DuplicateHabitException(String name) {
        super("El habito '" + name + "' ya existe. No se permiten duplicados");
    }
}
