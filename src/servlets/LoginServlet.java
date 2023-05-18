package servlets;

import db.Country;
import db.DBConnector;
import db.Item;
import db.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/sign-in")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String redirect = "/sign-in";

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = DBConnector.getUser(email);
        if (user != null) {
            if(user.getPassword().equals(password)){
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);
                redirect = "/profile";
            }
        }
        resp.sendRedirect(redirect);
    }
}
