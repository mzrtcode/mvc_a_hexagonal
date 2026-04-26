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

        habitRepository.findByName(requestDTO.name())
                .ifPresent(habit -> {
                    throw new DuplicateHabitException(habit.getName());
        });

        Habit newHabit = new Habit();
        newHabit.setName(requestDTO.name());

        if (requestDTO.logs() != null && !requestDTO.logs().isEmpty()) {

            for (LocalDateTime log : requestDTO.logs()) {
                LogEntry newLog = new LogEntry(log);
                newHabit.getLogEntries().add(newLog);
            }
        }

        Habit habitSaved = habitRepository.save(newHabit);
        log.info("Habito guardado correctamente :{}", newHabit.getName());

        return toHabitResponseDTO(habitSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HabitResponseDTO> findAllHabits() {
        log.info(habitRepository.findAll().toString());
        return habitRepository.findAll().stream()
                .map(this::toHabitResponseDTO)
                .toList();
    }

    @Override
    public List<HabitResponseDTO> findHabitsLoggedSince(int days) {

        LocalDateTime sinceDate = LocalDateTime.now().minusDays(days);

        List<Habit> habitsLoggedSince = habitRepository.findHabitsLoggedSince(sinceDate);

        return habitsLoggedSince.stream().map(this::toHabitResponseDTO).toList();
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
