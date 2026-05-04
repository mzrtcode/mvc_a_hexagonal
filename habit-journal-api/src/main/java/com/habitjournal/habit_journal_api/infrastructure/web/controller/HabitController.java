package com.habitjournal.habit_journal_api.infrastructure.web.controller;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.in.RetrieveHabitUseCase;
import com.habitjournal.habit_journal_api.infrastructure.web.controller.dto.HabitDTOMapper;
import com.habitjournal.habit_journal_api.infrastructure.web.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.infrastructure.web.controller.dto.HabitResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {
    private final CreateHabitUseCase createHabitUseCase;
    private final RetrieveHabitUseCase retrieveHabitUseCase;
    private final HabitDTOMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HabitResponseDTO createNewHabit(@Valid @RequestBody HabitRequestDTO requestDTO){
        Habit newHabit = createHabitUseCase.createHabit(mapper.toDomain(requestDTO));
        return mapper.toResponse(newHabit);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HabitResponseDTO> getAllHabits(){
        return retrieveHabitUseCase.getHabits().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HabitResponseDTO getHabitById(@PathVariable String id){
        Habit habit = retrieveHabitUseCase.getHabit(id);
        return mapper.toResponse(habit);

    }
}
