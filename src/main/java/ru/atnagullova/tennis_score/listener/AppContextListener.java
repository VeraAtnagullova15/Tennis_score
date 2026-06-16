package ru.atnagullova.tennis_score.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.atnagullova.tennis_score.dao.PlayerDao;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        PlayerDao playerDao = new PlayerDao();

        context.setAttribute("playerDao", playerDao);

    }
}

