package fr.laerce.facturation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.laerce.facturation.listeners.FacturationListener;
import fr.laerce.facturation.model.Client;
import fr.laerce.facturation.ListeClients;

public class Detail extends HttpServlet {
    Connection conn;


    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String loc = httpServletRequest.getParameter("loc");
        String pays = httpServletRequest.getParameter("pays");
        String nom = httpServletRequest.getParameter("nom");
//        String pnom = httpServletRequest.getParameter("pnom");
        try {

//            Statement req = conn.createStatement();
//            String update = "update clients set clt_loc='"+loc+"', clt_pays='"+pays+"' where clt_nom='"+nom+"'";
//            req.executeUpdate(update);
            PreparedStatement update = (PreparedStatement) getServletContext().getAttribute("update");
            update.setString (1, loc);
            update.setString (2, pays);
            update.setString (3, nom);
            update.execute ();
            httpServletResponse.sendRedirect("/index.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        List<Client> clients = (ArrayList) session.getAttribute("clients");
        String name = httpServletRequest.getParameter("id");

        for (Client c : clients) {
            String nomc = c.getNom();
            if (name.equals(nomc)) {
                httpServletRequest.setAttribute("client", c);
            }
        }
        String jspview = "detail.jsp";
        getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + jspview).forward(httpServletRequest, httpServletResponse);
    }

    public void init() throws ServletException {
        super.init();
        conn = (Connection) getServletContext().getAttribute("conn");
//        String url = getServletContext().getInitParameter("url");
//        String user = getServletContext().getInitParameter("user");
//        String password = getServletContext().getInitParameter("password");
//        String port = getServletContext().getInitParameter("port");
//        String bdd = getServletContext().getInitParameter("bdd");
//        String filename = url + bdd;
//        try {
//            Class.forName("org.postgresql.Driver");
//            Properties props = new Properties();
//            props.setProperty("user", user);
//            props.setProperty("password", password);
//            conn = DriverManager.getConnection(filename, props);
////            conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new ServletException("Pas de Driver SQL");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new ServletException("Pas de connexion à la base");
//        }
    }
}

