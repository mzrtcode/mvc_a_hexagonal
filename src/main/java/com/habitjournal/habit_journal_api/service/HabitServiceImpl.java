package com.habitjournal.habit_journal_api.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import com.habitjournal.habit_journal_api.service.exception.DuplicateHabitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    @Transactional
    public HabitResponseDTO createNewHabit(HabitRequestDTO requestDTO) {

        habitRepository.findHabitByName(requestDTO.name())
                .ifPresent(habit -> {
                    throw new DuplicateHabitException(habit.getName());
                });

        Habit newHabit = new Habit();
        newHabit.changeName(requestDTO.name());

        if (requestDTO.logs() != null && !requestDTO.logs().isEmpty()) {

            for (LocalDateTime log : requestDTO.logs()) {
                LogEntry newLog = new LogEntry();
                newHabit.addLogEntry(newLog);
            }
        }

        Habit habitSaved = habitRepository.save(newHabit);
        log.info("Habito guardado correctamente :{}", newHabit.getName());

        return toHabitResponseDTO(habitSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitResponseDTO> findAllHabits() {
        log.info(habitRepository.findAllWithLogs().toString());
        return habitRepository.findAllWithLogs().stream()
                .map(this::toHabitResponseDTO)
                .toList();
    }



    private HabitResponseDTO toHabitResponseDTO(Habit habit) {
       return new HabitResponseDTO(
                habit.getId(),
                habit.getName(),
                habit.getLogEntries().stream()
                        .map(LogEntry::getEntryDate)
                        .toList()
        );
    }

}
