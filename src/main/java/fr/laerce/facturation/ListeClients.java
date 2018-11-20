package fr.laerce.facturation;

import fr.laerce.facturation.model.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ListeClients extends HttpServlet {
    Connection conn;

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String loc = httpServletRequest.getParameter("loc");
        String pays = httpServletRequest.getParameter("pays");
        String nom = httpServletRequest.getParameter("nom");
        String pnom = httpServletRequest.getParameter("pnom");
        String nb;
        try {
            Statement req = conn.createStatement();
            ResultSet rs = req.executeQuery("SELECT count(*) FROM clients");
            rs.next();
            nb = "C"+ rs.getString(1);
            PreparedStatement insert = (PreparedStatement) getServletContext().getAttribute("insert");
            insert.setString (1, nb);
            insert.setString (2, nom);
            insert.setString (3, pnom);
            insert.setString (4, loc);
            insert.setString (5, pays);
            insert.execute ();
//            httpServletResponse.sendRedirect("/index.jsp");
//            String insert = "INSERT INTO clients (clt_num,clt_nom, clt_pnom, clt_loc, clt_pays) VALUES ('"+nb+"','"+nom+"','"+pnom+"','"+loc+"','"+pays+"')";
//            req.executeUpdate(insert);
            doGet(httpServletRequest,httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {

            Statement req = conn.createStatement();
            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients";
            ResultSet res = req.executeQuery(query);
            List<Client> clients = new ArrayList<Client>();
            while(res.next()){
                clients.add(new Client(res.getString("clt_num"),
                        res.getString("clt_nom"),
                        res.getString("clt_pnom"),
                        res.getString("clt_loc"),
                        res.getString("clt_pays")));
            }
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("clients", clients);
            httpServletRequest.setAttribute("clients", clients);
            String laVue = "clients.jsp";
            getServletConfig().getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(httpServletRequest, httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void init() throws ServletException {
        super.init();
        conn = (Connection) getServletContext().getAttribute("conn");
        System.out.println (conn);
//        String url= getServletContext().getInitParameter("url");
//        String user= getServletContext().getInitParameter("user");
//        String password= getServletContext().getInitParameter("password");
//        String port= getServletContext().getInitParameter("port");
//        String bdd= getServletContext().getInitParameter("bdd");
//        String filename =url+bdd;
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
//
    }
}
