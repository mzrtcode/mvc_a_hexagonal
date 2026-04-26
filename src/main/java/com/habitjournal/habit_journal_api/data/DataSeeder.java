package com.habitjournal.habit_journal_api.data;

import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataSeeder implements CommandLineRunner {

    private final HabitRepository habitRepository;

    @Override
    public void run(String... args) throws Exception {
        if (habitRepository.count() == 0) {
            Habit habit = new Habit();
            habit.changeName("Aprender Arquitectura");

            // Simulamos que cumplimos el hábito hoy y hace 2 días
            LogEntry log1 = new LogEntry();
            log1.setEntryDate(LocalDateTime.now().minusDays(2));

            LogEntry log2 = new LogEntry();
            log2.setEntryDate(LocalDateTime.now());

            habit.addLogEntry(log1);
            habit.addLogEntry(log2);

            // Guardamos (el CascadeType.ALL guardará los registros también)
            habitRepository.save(habit);

            log.info("✅ Datos de prueba cargados!");
        }
    }
}
