package ru.atnagullova.tennis_score.match_state;

import lombok.Getter;

import java.util.UUID;


@Getter
public class ActiveMatch {

    private final UUID id;

    private final Long playerOneId;

    private final Long playerTwoId;

    private int playerOnePoints;

    private int playerTwoPoints;

    private int playerOneGames;

    private int playerTwoGames;

    private int playerOneSets;

    private int playerTwoSets;

    public ActiveMatch(Long playerOneId, Long playerTwoId) {
        id = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
    }



}
