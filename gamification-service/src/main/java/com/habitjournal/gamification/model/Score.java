package com.habitjournal.gamification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Score {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String habitId;

    private int points;

    @PrePersist
    public void prePersist() {
        if (this.awardedAt == null) {
            this.awardedAt = LocalDateTime.now();
        }

    }

    private LocalDateTime awardedAt;

}
