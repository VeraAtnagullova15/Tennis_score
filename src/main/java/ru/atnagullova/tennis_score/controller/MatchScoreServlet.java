package ru.atnagullova.tennis_score.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.atnagullova.tennis_score.service.FinishedMatchesPersistenceService;
import ru.atnagullova.tennis_score.service.MatchScoreCalculationService;
import ru.atnagullova.tennis_score.service.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService calculationService;
    private FinishedMatchesPersistenceService matchesPersistenceService;

    public void init() {
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        calculationService = (MatchScoreCalculationService) getServletContext().getAttribute("calculationService");
        matchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext()
                .getAttribute("matchesPersistenceService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
