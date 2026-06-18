package ru.atnagullova.tennis_score.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.atnagullova.tennis_score.entity.Player;
import ru.atnagullova.tennis_score.exception.InvalidPlayerNameException;
import ru.atnagullova.tennis_score.match_state.ActiveMatch;
import ru.atnagullova.tennis_score.service.OngoingMatchesService;
import ru.atnagullova.tennis_score.service.PlayerService;
import ru.atnagullova.tennis_score.util.PlayerValidator;

import java.io.IOException;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private PlayerService playerService;
    private OngoingMatchesService ongoingMatchesService;

    public void init() {
        playerService = (PlayerService) getServletContext().getAttribute("playerService");
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/new-match.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String player1 = request.getParameter("playerOne");
        String player2 = request.getParameter("playerTwo");

        if (player1.equals(player2)) {
            throw new InvalidPlayerNameException("Player names cannot be the same");
        }

        Player playerOne = playerService.saveOrGet(player1);
        Player playerTwo = playerService.saveOrGet(player2);


        ActiveMatch activeMatch = new ActiveMatch(
                playerOne.getId(),
                playerTwo.getId());

        ongoingMatchesService.save(activeMatch);

        response.sendRedirect(
                request.getContextPath() + "/match-score?uuid=" + activeMatch.getId()
        );
    }

}
