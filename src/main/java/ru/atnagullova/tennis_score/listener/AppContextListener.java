package ru.atnagullova.tennis_score.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.atnagullova.tennis_score.dao.PlayerDao;
import ru.atnagullova.tennis_score.service.OngoingMatchesService;
import ru.atnagullova.tennis_score.service.PlayerService;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        PlayerDao playerDao = new PlayerDao();
        PlayerService playerService = new PlayerService(playerDao);
        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

        context.setAttribute("playerDao", playerDao);
        context.setAttribute("playerService", playerService);
        context.setAttribute("ongoingMatchesService", ongoingMatchesService);

    }
}

