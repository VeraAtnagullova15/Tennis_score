package ru.atnagullova.tennis_score.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;
import ru.atnagullova.tennis_score.match_state.Point;

public class MatchScoreCalculationServiceTest {
    private MatchScoreExample scoreExample = new MatchScoreExample();
    //    private MatchScoreCalculationService calculationService;
    private ActiveMatch activeMatch;

    @BeforeEach
    void setUp() {
//        calculationService = new MatchScoreCalculationService();
        activeMatch = new ActiveMatch(1L, 2L, "Vera", "Kate");
    }

    @Test
    void isWinGame_FortyFortyFirstPlayerNotWinTest() {

        Point point1 = activeMatch.getPlayerOneScore().getPoints();
        Point point2 = activeMatch.getPlayerTwoScore().getPoints();

        point1 = Point.FORTY;
        point2 = Point.FORTY;

        activeMatch.getPlayerOneScore().increasePoints();

        point1 = activeMatch.getPlayerOneScore().getPoints();


        Assertions.assertFalse(scoreExample.isGameWonEnum(activeMatch));
    }

    @Test
    void isWinGame_FortyLoveFirstPlayerWinTest() {

//        Point point1 = activeMatch.getPlayerOneScore().getPoints();
//        Point point2 = activeMatch.getPlayerTwoScore().getPoints();
//
//        point1 = Point.FORTY;
//        point2 = Point.LOVE;

        activeMatch.getPlayerOneScore().increasePoints();
        activeMatch.getPlayerOneScore().increasePoints();
        activeMatch.getPlayerOneScore().increasePoints();
        activeMatch.getPlayerOneScore().increasePoints();

        Assertions.assertTrue(scoreExample.isGameWonEnum(activeMatch));
    }

    @Test
    void isTieBreakStart_SixSixGamesTest() {

        activeMatch.getPlayerOneScore().setGame(6);
        activeMatch.getPlayerTwoScore().setGame(6);
        activeMatch.getPlayerOneScore().increasePoints();

        Assertions.assertTrue(scoreExample.isTieBreak(activeMatch));
    }


}
