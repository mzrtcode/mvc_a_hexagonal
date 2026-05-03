package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.LogEntry;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.HabitEntity;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.LogEntryEntity;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document.HabitDocument;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document.LogEntryDocument;
import org.springframework.stereotype.Component;

@Component
public class HabitMongoMapper {
    HabitDocument toDocument(Habit habit){
        HabitDocument habitDocument = new HabitDocument();
        habitDocument.setId(habit.getId());
        habitDocument.setName(habit.getName());

        if(!habit.getLogEntries().isEmpty()){
            for(LogEntry logEntry: habit.getLogEntries()){
                LogEntryDocument newLogDocument = new LogEntryDocument();
                newLogDocument.setEntryDate(logEntry.getEntryDate());

                habitDocument.addNewLogEntry(newLogDocument);
            }
        }


        return  habitDocument;
    }

    Habit toDomain(HabitDocument habitDocument){
        Habit habit = new Habit();
        habit.setId(habitDocument.getId());
        habit.setName(habitDocument.getName());

        if(!habitDocument.getLogEntries().isEmpty()){
            for(LogEntryDocument logEntryDocument : habitDocument.getLogEntries()){
                LogEntry logEntry = new LogEntry();
                logEntry.setEntryDate(logEntryDocument.getEntryDate());
                habit.addNewLogEntry(logEntry);
            }
        }
        return  habit;
    }
}
