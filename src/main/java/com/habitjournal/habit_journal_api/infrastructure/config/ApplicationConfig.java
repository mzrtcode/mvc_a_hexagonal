package com.habitjournal.habit_journal_api.infrastructure.config;

import com.habitjournal.habit_journal_api.application.HabitService;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.in.RetrieveHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateHabitUseCase createHabitUseCase(HabitRepositoryPort habitRepositoryPort){
        return new HabitService(habitRepositoryPort);
    }

    @Bean
    public RetrieveHabitUseCase retrieveHabitUseCase(HabitRepositoryPort habitRepositoryPort){
        return new HabitService(habitRepositoryPort);
    }
}
