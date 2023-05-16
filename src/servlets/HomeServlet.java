package servlets;

import db.Country;
import db.DBConnector;
import db.DBManager;
import db.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/home.html")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String priceFrom = req.getParameter("priceFrom");
        String priceTo = req.getParameter("priceTo");
        String countryId = req.getParameter("country_id");

        ArrayList<Country> countries = DBConnector.getCountries();
        req.setAttribute("strany", countries);

        if (name != null || priceFrom != null || priceTo != null) {
            ArrayList<Item> items = DBConnector.searchItems(name, priceFrom, priceTo, countryId);
            req.setAttribute("tovary", items);
        } else {
            ArrayList<Item> items = DBConnector.getItems();
            req.setAttribute("tovary", items);
        }

        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
