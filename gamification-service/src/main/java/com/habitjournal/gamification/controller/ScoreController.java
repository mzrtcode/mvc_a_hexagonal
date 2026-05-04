package com.habitjournal.gamification.controller;

import com.habitjournal.gamification.controller.dto.HabitCompletionRequestDTO;
import com.habitjournal.gamification.controller.dto.HabitCompletionResponseDTO;
import com.habitjournal.gamification.controller.mapper.ScoreMapper;
import com.habitjournal.gamification.repository.ScoreRepository;
import com.habitjournal.gamification.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gamification")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;
    private final ScoreMapper scoreMapper;

    @PostMapping("habit-completed")
    @ResponseStatus(HttpStatus.OK)
    public String registerHabitCompletion(@RequestBody HabitCompletionRequestDTO request) {
        scoreService.processHabitCompletion(request.userId(), request.habitId());
        return "Puntos asignados correctamente";
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HabitCompletionResponseDTO> getUserScores(@PathVariable Long userId) {

        return scoreService.getScoresByUserId(userId);

    }


}
