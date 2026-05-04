package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.repository;

import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitJpaRepository extends JpaRepository<HabitEntity, String> {
    Optional<HabitEntity> findByName(String name);
}
