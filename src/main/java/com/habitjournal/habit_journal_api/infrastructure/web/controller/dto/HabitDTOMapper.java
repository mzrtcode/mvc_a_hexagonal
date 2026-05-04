package com.habitjournal.habit_journal_api.infrastructure.web.controller.dto;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.LogEntry;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HabitDTOMapper {
     public Habit toDomain(HabitRequestDTO requestDTO){
         Habit habit = new Habit();
         habit.setName(requestDTO.name());

         if(!requestDTO.logs().isEmpty()){
             for (LocalDateTime logRequest: requestDTO.logs()){
                 LogEntry logEntry = new LogEntry();
                 logEntry.setEntryDate(logRequest);
                 habit.addNewLogEntry(logEntry);
             }
         }
         return habit;
     }

     public HabitResponseDTO toResponse(Habit habit){
         List<LocalDateTime> localDateTimes = habit.getLogEntries().stream().map(LogEntry::getEntryDate).toList();
         return new HabitResponseDTO(habit.getId(), habit.getName(), localDateTimes);

     }
}
