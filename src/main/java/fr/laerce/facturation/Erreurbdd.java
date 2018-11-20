package fr.laerce.facturation;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.LogRecord;

public class Erreurbdd extends HttpServlet implements Filter {

    Connection conn;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context=servletRequest.getServletContext ();

        try {
          conn = (Connection) context.getAttribute ("conn");
            filterChain.doFilter (servletRequest, servletResponse);
        } catch (NullPointerException e) {
            e.printStackTrace ();
            String laVue = "erreurbdd.jsp";
            context.getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(servletRequest, servletResponse); }
        try {
            if (!conn.isValid (1)) {String laVue = "erreurbdd.jsp";
                context.getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(servletRequest, servletResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
            String laVue = "index.jsp";
            context.getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(servletRequest, servletResponse);

        }

//        try {
//            Class.forName("org.postgresql.Driver");
//            Properties props = new Properties();
//            props.setProperty("user", "postgres");
//            props.setProperty("password", "secret");
//            conn1 = DriverManager.getConnection("jdbc:postgresql://192.168.99.100/exemple", props);
////            context.setAttribute("conn", conn);
//    }catch (NullPointerException e) {
//            e.printStackTrace ();
//            String laVue = "erreurbdd.jsp";
//            context.getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(servletRequest, servletResponse); }
//            catch (ClassNotFoundException e) {
//                String laVue = "erreurbdd.jsp";
//                context.getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(servletRequest, servletResponse);
//        e.printStackTrace ();
//        } catch (SQLException e) {
//            e.printStackTrace ();
//        String laVue = "erreurbdd.jsp";
//        context.getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(servletRequest, servletResponse); }
}

    }






