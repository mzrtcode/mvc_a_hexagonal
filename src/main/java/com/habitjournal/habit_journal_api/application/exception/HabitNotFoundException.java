package com.habitjournal.habit_journal_api.application.exception;

public class HabitNotFoundException extends RuntimeException {

    private Long id;

    public HabitNotFoundException(Long id) {
        super("No se encontro un habito con el id " + id);
        this.id = id;
    }
    public Long  getId(){
        return this.id;
    }
}
