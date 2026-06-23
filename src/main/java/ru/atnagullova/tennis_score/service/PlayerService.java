package ru.atnagullova.tennis_score.service;

import lombok.AllArgsConstructor;
import ru.atnagullova.tennis_score.dao.PlayerDao;
import ru.atnagullova.tennis_score.entity.Player;


@AllArgsConstructor
public class PlayerService {

    private PlayerDao playerDao;

    public Player saveOrGet(String name) {
        return playerDao.find(name)
                .orElseGet(() -> playerDao.saveAndReturn(name));
    }

}
