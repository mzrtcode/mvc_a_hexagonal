package com.habitjournal.gamification.service;

import com.habitjournal.gamification.controller.dto.HabitCompletionResponseDTO;
import com.habitjournal.gamification.controller.mapper.ScoreMapper;
import com.habitjournal.gamification.model.Score;
import com.habitjournal.gamification.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    private static final int POINTS_PER_HABIT = 10;


    @Override
    public void processHabitCompletion(Long userId, String habitId) {

        Score score = Score.builder()
                        .userId(userId)
                        .habitId(habitId)
                        .points(POINTS_PER_HABIT)
                        .build();

        log.info("--- GAMIFICATION: +10 Puntos guardados ---");
        scoreRepository.save(score);
    }



    @Override
    public List<HabitCompletionResponseDTO> getScoresByUserId(Long userId) {

        return scoreRepository.findByUserId(userId).stream().map(scoreMapper::toScoreResponse).toList();

    }
}
