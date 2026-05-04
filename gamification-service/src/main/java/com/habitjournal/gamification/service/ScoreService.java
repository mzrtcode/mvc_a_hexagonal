package com.habitjournal.gamification.service;

import com.habitjournal.gamification.controller.dto.HabitCompletionResponseDTO;

import java.util.List;

public interface ScoreService {

    void processHabitCompletion(Long userId, String habitId);
    List<HabitCompletionResponseDTO> getScoresByUserId(Long userId);
}
