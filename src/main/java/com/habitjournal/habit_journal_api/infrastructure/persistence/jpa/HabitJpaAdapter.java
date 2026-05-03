package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.HabitEntity;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.repository.HabitJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HabitJpaAdapter implements HabitRepositoryPort {

    private final HabitJpaRepository repository;
    private final HabitJpaMapper mapper;

    @Override
    public Habit save(Habit habit) {
        HabitEntity savedEntity = repository.save(mapper.toEntity(habit));
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Habit> findByName(String name) {
       return repository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public List<Habit> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Habit> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
