package servlets;

import db.DBConnector;
import db.Item;
import db.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/delete-item")
public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if(currentUser!=null) {
            int id = Integer.parseInt(req.getParameter("item_id"));
            DBConnector.deleteItem(id);
            resp.sendRedirect("/");
        }else {
            resp.sendRedirect("/sign-in");
        }
    }
}