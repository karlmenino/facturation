package fr.laerce.facturation.listeners;

import javax.servlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FacturationListener implements ServletContextListener {


    Connection conn;

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url= context.getInitParameter("url");
        String user= context.getInitParameter("user");
        String password= context.getInitParameter("password");
        String port= context.getInitParameter("port");
        String bdd= context.getInitParameter("bdd");
        String filename =url+bdd;
            try {
                Class.forName("org.postgresql.Driver");
                Properties props = new Properties();
                props.setProperty("user", user);
                props.setProperty("password", password);
                conn = DriverManager.getConnection(filename, props);
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);
                context.setAttribute("conn", conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
