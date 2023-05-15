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
import java.util.ArrayList;

@WebServlet(value = "/add-item")
public class AddItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Country> countries = DBConnector.getCountries();
        req.setAttribute("strany", countries);
        req.getRequestDispatcher("/additem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("item_name");
        String price = req.getParameter("item_price");
        int countryId = Integer.parseInt(req.getParameter("country_id"));

        Country country = DBConnector.getCountry(countryId);
        if(country!=null) {
            Item item = new Item();
            item.setName(name);
            item.setPrice(Integer.parseInt(price));
            item.setCountry(country);
            DBConnector.addItem(item);
        }

        resp.sendRedirect("/");
    }
}