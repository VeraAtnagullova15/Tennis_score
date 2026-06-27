package ru.atnagullova.tennis_score.service;

import lombok.AllArgsConstructor;
import ru.atnagullova.tennis_score.dao.MatchDao;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;

@AllArgsConstructor
public class FinishedMatchesPersistenceService {

    private MatchDao matchDao;

    public void save(ActiveMatch activeMatch) {
        matchDao.save(activeMatch);
    }
}
