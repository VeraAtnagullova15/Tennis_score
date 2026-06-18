package ru.atnagullova.tennis_score.service;

import lombok.AllArgsConstructor;
import ru.atnagullova.tennis_score.dao.PlayerDao;
import ru.atnagullova.tennis_score.entity.Player;
import ru.atnagullova.tennis_score.exception.InvalidPlayerNameException;
import ru.atnagullova.tennis_score.util.PlayerValidator;

import java.util.Optional;

@AllArgsConstructor
public class PlayerService {

    private PlayerDao playerDao;

    public Player saveOrGet(String name) {

        if (!PlayerValidator.isValidPlayerName(name)) {
            throw new InvalidPlayerNameException("Player name cannot be blank");
        }

        return playerDao.find(name)
                .orElseGet(() -> playerDao.saveAndReturn(name));
    }

}
