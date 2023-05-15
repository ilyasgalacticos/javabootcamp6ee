package servlets;

import db.Country;
import db.DBConnector;
import db.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/save-item")
public class SaveItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("item_id"));
        String name = req.getParameter("item_name");
        String price = req.getParameter("item_price");
        int countryId = Integer.parseInt(req.getParameter("country_id"));

        Country country = DBConnector.getCountry(countryId);
        Item item = DBConnector.getItem(id);

        if (item != null && country != null) {
            item.setName(name);
            item.setPrice(Integer.parseInt(price));
            item.setCountry(country);
            DBConnector.saveItem(item);
            resp.sendRedirect("/details?idshka=" + item.getId());
        } else {
            resp.sendRedirect("/");
        }
    }
}