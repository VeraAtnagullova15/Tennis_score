package ru.atnagullova.tennis_score.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.atnagullova.tennis_score.dao.PlayerDao;

import java.io.IOException;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private PlayerDao playerDao;

    public void init() {
        playerDao = (PlayerDao) getServletContext().getAttribute("playerDao");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/new-match.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String player1 = request.getParameter("playerOne");
        String player2 = request.getParameter("playerTwo");

        if (player1 != null && !playerDao.isPlayerExist(player1)) {

            playerDao.persistPlayer(player1);
        }

        if (player2 != null && !playerDao.isPlayerExist(player2)) {

            playerDao.persistPlayer(player2);
        }



    }

}
