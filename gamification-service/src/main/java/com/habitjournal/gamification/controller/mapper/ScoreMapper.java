package com.habitjournal.gamification.controller.mapper;

import com.habitjournal.gamification.controller.dto.HabitCompletionResponseDTO;
import com.habitjournal.gamification.model.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScoreMapper {

    HabitCompletionResponseDTO toScoreResponse(Score score);
}
