package servlets;

import db.DBConnector;
import db.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/sign-up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String redirect = "/sign-up?code=password";

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");
        String fullName = req.getParameter("full_name");

        if (password.equals(rePassword)) {
            User user = DBConnector.getUser(email);
            redirect = "/sign-up?code=email";

            if (user == null) {

                User createdUser = new User();
                createdUser.setEmail(email);
                createdUser.setPassword(password);
                createdUser.setFullName(fullName);

                DBConnector.addUser(createdUser);
                redirect = "/sign-up?code=success";
            }
        }
        resp.sendRedirect(redirect);
    }
}
