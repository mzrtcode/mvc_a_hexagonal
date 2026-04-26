package com.habitjournal.habit_journal_api.data;

import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataSeeder implements CommandLineRunner {

    private final HabitRepository habitRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection(Habit.class);

        Habit habit = new Habit();
            habit.setName("Aprender Arquitectura");

        // Simulamos que cumplimos el hábito hoy y hace 2 días
        LogEntry log1 = new LogEntry();
            log1.setEntryDate(LocalDateTime.now().minusDays(2));

        LogEntry log2 = new LogEntry();
        log2.setEntryDate(LocalDateTime.now());

        habit.getLogEntries().add(log1);
        habit.getLogEntries().add(log2);

        // Guardamos (el CascadeType.ALL guardará los registros también)
        habitRepository.save(habit);

        log.info("✅ Datos de prueba cargados!");
        }

}
