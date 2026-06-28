package ru.atnagullova.tennis_score.match_state;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PlayerScore {

    private static final int ZERO = 0;

    @Setter
    private int point;

    private Point points;
    private int counterStepsInGame;
    @Setter
    private int game;
    private int set;
    private int tieBreakPoint;

    public PlayerScore() {
        points = Point.LOVE;
    }

    public void increasePoints() {
        point++;
        points = points.next();
        increaseCounterStepsInGame();

    }

    public void resetPoints() {
        point = ZERO;
        points = Point.LOVE;
        resetCounterStepsInGame();
    }

    public void doStepBackPoints() {
        points = points.stepBack();
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

    private void increaseCounterStepsInGame() {
        counterStepsInGame++;
    }

    private void resetCounterStepsInGame() {
        counterStepsInGame = ZERO;
    }
}