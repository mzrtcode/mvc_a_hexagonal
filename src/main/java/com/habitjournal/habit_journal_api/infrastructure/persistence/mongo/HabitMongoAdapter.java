package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo;

import com.habitjournal.habit_journal_api.common.IdGenerator;
import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document.HabitDocument;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.repository.HabitMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class HabitMongoAdapter implements HabitRepositoryPort {

    private final HabitMongoRepository habitMongoRepository;
    private final HabitMongoMapper habitMongoMapper;


    @Override
    public Habit save(Habit habit) {
        HabitDocument habitDocument = habitMongoMapper.toDocument(habit);

        if(habitDocument.getId() == null){
            habitDocument.setId(IdGenerator.nextId());
        }

        HabitDocument savedDocument = habitMongoRepository.save(habitDocument);

        return habitMongoMapper.toDomain(savedDocument);
    }

    @Override
    public Optional<Habit> findByName(String name) {

        Optional<HabitDocument> habitDocument = habitMongoRepository.findByName(name);
        return habitDocument.map(habitMongoMapper::toDomain);

    }

    @Override
    public List<Habit> findAll() {
        List<HabitDocument> allDocuments = habitMongoRepository.findAll();
        return allDocuments.stream().map(habitMongoMapper::toDomain).toList();
    }

    @Override
    public Optional<Habit> findById(String id) {
        Optional<HabitDocument> habitDocument = habitMongoRepository.findById(id);
        return habitDocument.map(habitMongoMapper::toDomain);
    }
}
