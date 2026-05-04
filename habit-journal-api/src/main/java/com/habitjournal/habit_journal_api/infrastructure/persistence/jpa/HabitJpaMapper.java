package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.LogEntry;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.HabitEntity;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.LogEntryEntity;
import org.springframework.stereotype.Component;

@Component
public class HabitJpaMapper {
    HabitEntity toEntity(Habit habit){
        HabitEntity habitEntity = new HabitEntity();
        habitEntity.setId(habit.getId());
        habitEntity.setName(habit.getName());

        if(!habit.getLogEntries().isEmpty()){
            for(LogEntry logEntry: habit.getLogEntries()){
                LogEntryEntity newLogEntity = new LogEntryEntity();
                newLogEntity.setEntryDate(logEntry.getEntryDate());

                habitEntity.addNewLogEntry(newLogEntity);
            }
        }


        return  habitEntity;
    }

    Habit toDomain(HabitEntity habitEntity){
        Habit habit = new Habit();
        habit.setId(habitEntity.getId());
        habit.setName(habitEntity.getName());

        if(!habitEntity.getLogEntries().isEmpty()){
            for(LogEntryEntity logEntryEntity : habitEntity.getLogEntries()){
                LogEntry logEntry = new LogEntry();
                logEntry.setEntryDate(logEntryEntity.getEntryDate());
                habit.addNewLogEntry(logEntry);
            }
        }
        return  habit;
    }
}
