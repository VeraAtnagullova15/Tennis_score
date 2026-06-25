package ru.atnagullova.tennis_score.service;

import ru.atnagullova.tennis_score.match_state.ActiveMatch;


public class MatchScoreCalculationService {

    private static final int MIN_NUMBER_OF_REGULAR_POINTS = 3;
    private static final int MIN_GAMES_WIN_SET = 6;
    private static final int DIFFERENCE_FOR_WINNING_GAME = 2;
    private static final int DIFFERENCE_FOR_WINNING_SET = 2;
    private static final int NUMBER_OF_GAMES_FOR_TIE_BREAK = 6;
    private static final int MIN_POINTS_WIN_TIE_BREAK = 7;


    public void addPoint(ActiveMatch activeMatch, Long winnerId) {

        if (!isTieBreak(activeMatch)) {
            activeMatch.scoreOf(winnerId).increasePoints();

            if (isGameWon(activeMatch)) {
                activeMatch.getPlayerOneScore().resetPoints();
                activeMatch.getPlayerTwoScore().resetPoints();
                addGame(activeMatch, winnerId);
            }

        } else {
            addTieBreak(activeMatch, winnerId);
        }
    }

    private boolean isGameWon(ActiveMatch activeMatch) {
        int player1 = activeMatch.getPlayerOneScore().getPoint();
        int player2 = activeMatch.getPlayerTwoScore().getPoint();
        return (player1 >= MIN_NUMBER_OF_REGULAR_POINTS
                || player2 >= MIN_NUMBER_OF_REGULAR_POINTS)
                && (Math.abs(player1 - player2) >= DIFFERENCE_FOR_WINNING_GAME);
    }

    public void addGame(ActiveMatch activeMatch, Long winnerId) {

        activeMatch.scoreOf(winnerId).increaseGames();

        if (isSetWon(activeMatch)) {
            activeMatch.getPlayerOneScore().resetGames();
            activeMatch.getPlayerTwoScore().resetGames();
            addSet(activeMatch, winnerId);
        }
    }

    private boolean isTieBreak(ActiveMatch activeMatch) {
        int player1 = activeMatch.getPlayerOneScore().getGame();
        int player2 = activeMatch.getPlayerTwoScore().getGame();
        return (player1 == NUMBER_OF_GAMES_FOR_TIE_BREAK
                && player2 == NUMBER_OF_GAMES_FOR_TIE_BREAK);
    }

    public void addTieBreak(ActiveMatch activeMatch, Long winnerId) {

        activeMatch.scoreOf(winnerId).increaseTieBreakPoints();

        if (isTieBreakWon(activeMatch)) {
            activeMatch.getPlayerOneScore().resetPoints();
            activeMatch.getPlayerTwoScore().resetPoints();
            addSet(activeMatch, winnerId);
        }
    }

    private boolean isSetWon(ActiveMatch activeMatch) {
        return isTieBreak(activeMatch) ? isTieBreakWon(activeMatch) : isRegularSetWon(activeMatch);
    }

    private boolean isRegularSetWon(ActiveMatch activeMatch) {
        int player1 = activeMatch.getPlayerOneScore().getGame();
        int player2 = activeMatch.getPlayerTwoScore().getGame();
        return (player1 >= MIN_GAMES_WIN_SET
                || player2 >= MIN_GAMES_WIN_SET)
                && (Math.abs(player1 - player2) >= DIFFERENCE_FOR_WINNING_SET);
    }

    private boolean isTieBreakWon(ActiveMatch activeMatch) {
        int player1 = activeMatch.getPlayerOneScore().getTieBreakPoint();
        int player2 = activeMatch.getPlayerTwoScore().getTieBreakPoint();
        return (player1 >= MIN_POINTS_WIN_TIE_BREAK
                || player2 >= MIN_POINTS_WIN_TIE_BREAK)
                && (Math.abs(player1 - player2) >= DIFFERENCE_FOR_WINNING_SET);
    }

    public void addSet(ActiveMatch activeMatch, Long winnerId) {
        activeMatch.scoreOf(winnerId).increaseSets();
    }
}
