package fr.laerce.facturation.listeners;

import javax.servlet.*;
import java.sql.*;
import java.util.Objects;
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
                conn = Objects.requireNonNull (DriverManager.getConnection(filename, props));
                String updte = "update clients set clt_loc= ?,clt_pays=? where clt_nom=?";
                PreparedStatement update = conn.prepareStatement (updte);
                String insrt = "INSERT INTO clients (clt_num,clt_nom, clt_pnom, clt_loc, clt_pays) VALUES (?,?,?,?,?)";;
                PreparedStatement insert = conn.prepareStatement (insrt);
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);
                context.setAttribute("conn", conn);
                context.setAttribute ("update", update);
                context.setAttribute ("insert", insert);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
//              System.out.println(e.getMessage());

            } catch (SQLException e) {
                e.printStackTrace();
//               System.out.println(e.getMessage());
            }

        }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }




}
