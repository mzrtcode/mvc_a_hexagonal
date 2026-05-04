package com.habitjournal.habit_journal_api.infrastructure.web.client;

import com.habitjournal.habit_journal_api.domain.ports.out.GamificationPort;
import com.habitjournal.habit_journal_api.infrastructure.web.client.dto.HabitCompletedRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GamificationAdapter implements GamificationPort {

    private final GamificationHttpClient gamificationHttpClient;

    @Override
    public void notifyHabitCreation(Long userId, String habitId) {
        HabitCompletedRequest request = new HabitCompletedRequest(userId, habitId);

        try{
            gamificationHttpClient.notifyHabitCompletion(request);
            log.info("✅ Notificacion enviada a Gamification");
        }catch (Exception e){
            log.error("⚠️ Error al notificar notificacion enviada a Gamification: {}", e.getMessage());
        }
    }
}
