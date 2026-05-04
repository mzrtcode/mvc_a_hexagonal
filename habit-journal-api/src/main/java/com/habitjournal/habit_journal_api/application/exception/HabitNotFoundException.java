package com.habitjournal.habit_journal_api.application.exception;

public class HabitNotFoundException extends RuntimeException {

    private String id;

    public HabitNotFoundException(String id) {
        super("No se encontro un habito con el id " + id);
        this.id = id;
    }
    public String  getId(){
        return this.id;
    }
}
