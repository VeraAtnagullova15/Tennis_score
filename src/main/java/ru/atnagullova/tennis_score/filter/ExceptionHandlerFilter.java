package ru.atnagullova.tennis_score.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import ru.atnagullova.tennis_score.exception.InvalidPlayerNameException;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(value = {"/new-match", "/match-score"})
public class ExceptionHandlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        PrintWriter printWriter = response.getWriter();

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (InvalidPlayerNameException playerNameException) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
