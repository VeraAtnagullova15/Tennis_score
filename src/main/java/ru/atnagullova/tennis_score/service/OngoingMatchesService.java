package ru.atnagullova.tennis_score.service;

import ru.atnagullova.tennis_score.match_state.ActiveMatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private final Map<UUID, ActiveMatch> activeMatches = new ConcurrentHashMap<>();

    public void save(ActiveMatch activeMatch) {
        activeMatches.put(activeMatch.getId(), activeMatch);
    }

    public ActiveMatch get(UUID uuid) {
        return activeMatches.get(uuid);
    }

    public void remove(UUID uuid) {
        activeMatches.remove(uuid);
    }
}
