package servlets;

import db.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idshka");
        try{
            int idInt = Integer.parseInt(id);
            Item item = DBConnector.getItem(idInt);
            if(item!=null){
                req.setAttribute("tovar", item);
                ArrayList<Comment> comments = DBConnector.getComments(item.getId());
                req.setAttribute("comments", comments);
            }
            ArrayList<Country> countries = DBConnector.getCountries();
            req.setAttribute("strany", countries);

        }catch (Exception e){
        }
        req.getRequestDispatcher("/details.jsp").forward(req, resp);
    }
}
