package com.habitjournal.habit_journal_api.infrastructure.web.client;

import com.habitjournal.habit_journal_api.infrastructure.web.client.dto.HabitCompletedRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface GamificationHttpClient {

    @PostExchange("/api/v1/gamification/habit-completed")
    void notifyHabitCompletion(@RequestBody HabitCompletedRequest request);
}
