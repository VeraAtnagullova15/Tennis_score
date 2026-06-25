package ru.atnagullova.tennis_score.match_state;

import lombok.Getter;

@Getter
public class PlayerScore {

    private static final int ZERO = 0;

    private int point;
    private int game;
    private int set;
    private int tieBreakPoint;

    public PlayerScore() {
    }

    public void increasePoints() {
        point++;
    }

    public void resetPoints() {
        point = ZERO;
    }

    public void increaseGames() {
        game++;
    }


    public void resetGames() {
        game = ZERO;
    }

    public void increaseSets() {
        set++;
    }


    public void increaseTieBreakPoints() {
        tieBreakPoint++;
    }


    public void resetTieBreak() {
        tieBreakPoint = ZERO;
    }
}