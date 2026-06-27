package ru.atnagullova.tennis_score.match_state;

import lombok.Getter;

import java.util.UUID;


@Getter
public class ActiveMatch {

    private static final int NUMBER_SET_WIN_MATCH = 2;

    private final UUID id;

    private final Long playerOneId;
    private final Long playerTwoId;

    private final String playerOneName;
    private final String playerTwoName;

    private PlayerScore playerOneScore;
    private PlayerScore playerTwoScore;

    public ActiveMatch(Long playerOneId, Long playerTwoId, String playerOneName, String playerTwoName) {
        id = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        playerOneScore = new PlayerScore();
        playerTwoScore = new PlayerScore();
    }

    public PlayerScore scoreOf(Long playerId) {
        return playerOneId.equals(playerId) ? playerOneScore : playerTwoScore;
    }

    public boolean isMatchFinished() {
        return (playerOneScore.getSet() >= NUMBER_SET_WIN_MATCH
                || playerTwoScore.getSet() >= NUMBER_SET_WIN_MATCH);
    }

    public Long getWinnerId() {
        return playerOneScore.getSet() >= NUMBER_SET_WIN_MATCH ? playerOneId : playerTwoId;
    }

}
