package fr.laerce.facturation;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.LogRecord;

public class Erreurbdd extends HttpServlet implements Filter {
Connection conn;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        conn = (Connection) getServletContext ().getAttribute ("conn");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            if (conn == null) {

            }

        } catch () {
        }
    }
}


