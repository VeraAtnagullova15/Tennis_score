package ru.atnagullova.tennis_score.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;

public class MatchScoreCalculationServiceTest {

    private MatchScoreCalculationService calculationService;
    private ActiveMatch activeMatch;

    @BeforeEach
    void setUp() {
        calculationService = new MatchScoreCalculationService();
        activeMatch = new ActiveMatch(1L, 2L, "Vera", "Kate");
    }

    @Test
    void isWinGame_FortyFortyFirstPlayerNotWinTest() {

        activeMatch.getPlayerOneScore().setPoint(3);
        activeMatch.getPlayerTwoScore().setPoint(3);
        activeMatch.getPlayerOneScore().increasePoints();

        Assertions.assertFalse(calculationService.isGameWon(activeMatch));
    }

    @Test
    void isWinGame_FortyLoveFirstPlayerWinTest() {

        activeMatch.getPlayerOneScore().setPoint(3);
        activeMatch.getPlayerTwoScore().setPoint(0);
        activeMatch.getPlayerOneScore().increasePoints();

        Assertions.assertTrue(calculationService.isGameWon(activeMatch));
    }

    @Test
    void isTieBreakStart_SixSixGamesTest() {

        activeMatch.getPlayerOneScore().setGame(6);
        activeMatch.getPlayerTwoScore().setGame(6);
        activeMatch.getPlayerOneScore().increasePoints();

        Assertions.assertTrue(calculationService.isTieBreak(activeMatch));
    }


}
