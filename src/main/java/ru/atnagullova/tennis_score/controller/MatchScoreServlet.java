package ru.atnagullova.tennis_score.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.atnagullova.tennis_score.mapper.MatchScoreMapper;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;
import ru.atnagullova.tennis_score.model.MatchScoreModel;
import ru.atnagullova.tennis_score.service.FinishedMatchesPersistenceService;
import ru.atnagullova.tennis_score.service.MatchScoreCalculationService;
import ru.atnagullova.tennis_score.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

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

        String matchUuid = request.getParameter("uuid");
        UUID uuid = UUID.fromString(matchUuid);

        ActiveMatch activeMatch = ongoingMatchesService.get(uuid);
        MatchScoreModel scoreModel = MatchScoreMapper.INSTANCE.toMatchScoreModel(activeMatch);

        request.setAttribute("match", scoreModel);
        request.setAttribute("uuid", uuid);

        request.getRequestDispatcher("/match-score.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String matchUuid = request.getParameter("uuid");
        UUID uuid = UUID.fromString(matchUuid);

        Long winnerId = Long.parseLong(request.getParameter("playerId"));

        ActiveMatch activeMatch = ongoingMatchesService.get(uuid);

        calculationService.addPoint(activeMatch, winnerId);

        MatchScoreModel scoreModel = MatchScoreMapper.INSTANCE.toMatchScoreModel(activeMatch);

        if (activeMatch.isMatchFinished()) {
            request.setAttribute("match", scoreModel);
            response.sendRedirect(request.getContextPath()  + "/match-score?uuid=" + matchUuid);
            matchesPersistenceService.save(activeMatch);
            ongoingMatchesService.remove(uuid);
        } else {
            response.sendRedirect(request.getContextPath()  + "/match-score?uuid=" + matchUuid);
        }

    }


}
