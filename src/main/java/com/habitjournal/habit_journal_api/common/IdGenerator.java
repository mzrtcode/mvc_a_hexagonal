package com.habitjournal.habit_journal_api.common;

import java.util.UUID;

public class IdGenerator {

    public static String nextId() {
        return UUID.randomUUID().toString();
    }
}
