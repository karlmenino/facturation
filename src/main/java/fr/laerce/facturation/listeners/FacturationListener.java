package fr.laerce.facturation.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FacturationListener implements ServletContextListener {


    Connection conn;

    public void contextInitialized(ServletContextEvent sce) {
            try {
                Class.forName("org.postgresql.Driver");
                Properties props = new Properties();
                props.setProperty("user", "postgres");
                props.setProperty("password", "secret");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.99.100/exemple", props);
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);
                sce.getServletContext().setAttribute("conn", conn);
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


    public Connection getConn() {
        return conn;
    }
}
