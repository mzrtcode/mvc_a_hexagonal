package com.habitjournal.habit_journal_api.controller;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import com.habitjournal.habit_journal_api.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;
    private final HabitRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HabitResponseDTO createNewHabit(@Valid @RequestBody HabitRequestDTO requestDTO){
        return habitService.createNewHabit(requestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HabitResponseDTO> findAllHabits(){
        return habitService.findAllHabits();
    }

}
