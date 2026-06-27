package ru.atnagullova.tennis_score.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScoreModel {

    private Long playerOneId;
    private Long playerTwoId;
    private String playerOneName;
    private String playerTwoName;
    private int playerOneSets;
    private int playerTwoSets;
    private int playerOneGames;
    private int playerTwoGames;
    private String playerOnePoints;
    private String playerTwoPoints;

}
