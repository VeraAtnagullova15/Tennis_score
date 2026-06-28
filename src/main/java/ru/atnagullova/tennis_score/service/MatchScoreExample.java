package ru.atnagullova.tennis_score.service;

import ru.atnagullova.tennis_score.match_state.ActiveMatch;
import ru.atnagullova.tennis_score.match_state.Point;

public class MatchScoreExample {


        private static final int MIN_GAMES_WIN_SET = 6;
        private static final int DIFFERENCE_FOR_WINNING_SET = 2;
        private static final int NUMBER_OF_GAMES_FOR_TIE_BREAK = 6;
        private static final int MIN_POINTS_WIN_TIE_BREAK = 7;


        public void increasePoint(ActiveMatch activeMatch, Long winnerId) {

            if (!isTieBreak(activeMatch)) {
                activeMatch.scoreOf(winnerId).increasePoints();

                if (activeMatch.getPlayerOneScore().getPoints() == Point.AD
                        && activeMatch.getPlayerTwoScore().getPoints() == Point.AD) {
                    activeMatch.getPlayerOneScore().doStepBackPoints();
                    activeMatch.getPlayerTwoScore().doStepBackPoints();
                }

                if (isGameWonEnum(activeMatch)) {
                    addGame(activeMatch, winnerId);
                    activeMatch.getPlayerOneScore().resetPoints();
                    activeMatch.getPlayerTwoScore().resetPoints();
                }
            } else {
                addTieBreak(activeMatch, winnerId);
            }
        }

    public boolean isGameWonEnum(ActiveMatch activeMatch) {
        int playerOnePoints = activeMatch.getPlayerOneScore().getPoints().ordinal();
        int playerTwoPoints = activeMatch.getPlayerTwoScore().getPoints().ordinal();
        int playerOneSteps = activeMatch.getPlayerOneScore().getCounterStepsInGame();
        int playerTwoSteps = activeMatch.getPlayerTwoScore().getCounterStepsInGame();

        if ((playerOnePoints <= 2 && playerTwoPoints == 4)
                || (playerTwoPoints <= 2 && playerOnePoints == 4)) {
            return true;
        }
        if ((playerOnePoints >= 3 || playerTwoPoints >= 3)
                && ((playerOneSteps >= 5)
                || (playerTwoSteps >= 5))
                && Math.abs(playerOneSteps - playerTwoSteps) >= 2) {
            return true;
        }
        return false;
    }

        public void addGame(ActiveMatch activeMatch, Long winnerId) {

            activeMatch.scoreOf(winnerId).increaseGames();

            if (isSetWon(activeMatch)) {
                activeMatch.getPlayerOneScore().resetGames();
                activeMatch.getPlayerTwoScore().resetGames();
                addSet(activeMatch, winnerId);
            }
        }

        public boolean isTieBreak(ActiveMatch activeMatch) {
            int player1 = activeMatch.getPlayerOneScore().getGame();
            int player2 = activeMatch.getPlayerTwoScore().getGame();
            return (player1 == NUMBER_OF_GAMES_FOR_TIE_BREAK
                    && player2 == NUMBER_OF_GAMES_FOR_TIE_BREAK);
        }

        public void addTieBreak(ActiveMatch activeMatch, Long winnerId) {

            activeMatch.scoreOf(winnerId).increaseTieBreakPoints();

            if (isTieBreakWon(activeMatch)) {
                activeMatch.getPlayerOneScore().resetGames();
                activeMatch.getPlayerTwoScore().resetGames();
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

