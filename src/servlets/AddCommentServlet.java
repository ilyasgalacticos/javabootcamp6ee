package servlets;

import db.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add-comment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String redirect = "/sign-in";

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if(currentUser!=null) {

            redirect = "/";

            String comment = req.getParameter("comment");
            int itemId = Integer.parseInt(req.getParameter("item_id"));

            Item item = DBConnector.getItem(itemId);
            if (item != null) {
                Comment commentObj = new Comment();
                commentObj.setComment(comment);
                commentObj.setUser(currentUser);
                commentObj.setItem(item);

                DBConnector.addComment(commentObj);
                redirect = "/details?idshka="+item.getId();
            }
        }
        resp.sendRedirect(redirect);
    }
}